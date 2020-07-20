package com.elianda.test9.test

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/3/6 14:06
  *
  */
object DF_DSuser {
  //函数的定义
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]")
      .appName("xx").getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val new_ds = spark.read.format("json").load("E:\\01_myselfProject\\spark_all_project\\spark_streaming\\src\\main\\test.json")

    new_ds.show(false)

   val df_2 =  new_ds.groupBy("t_age","t_name").sum("t_age")//.show(false)


  import spark.implicits._
    //ds最大的不同是可以知道类型
  val ds = new_ds.as[People]
    println(ds.schema.fields.mkString(","))


    ds.map(_.t_age).show(false)  //这个用法很经典

    println("------------------------------------")
    ds.createTempView("new_table")
    spark.sql("select * from new_table").show  // 这里可以写sql了


    new_ds.writeStream
      .outputMode("complete")
      .format("console")
      .start
      .awaitTermination()

  }
}

//类型： int, 还是其他  ----不能重命名
case class People(t_name:String ,t_age:Long,t_sex:String)