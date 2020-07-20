package com.elianda.test9.test

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/3/6 22:13
  *
  */
object DF_test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]")
      .appName("xx").getOrCreate()

    spark.sparkContext.setLogLevel("WARN")
    import spark.implicits._

    val rdd = spark.sparkContext.parallelize(Array(("nihao",12),("beijing",20)))//,Array("1","2"))

    val col = Seq("name","age")
//    Old column names (2): _1, _2
//    New column names (1): name

    rdd.toDF(col:_*).show(false)// 给列赋值列名
    val ds_1 = rdd.toDS()
    val df_1 = rdd.toDF()
    println(ds_1.schema.fields.mkString(","))
    println(df_1.schema.fields.mkString(","))


    println("=========================='")
    val rdd2 = spark.sparkContext.parallelize(Array(People2("小米",20L,"man")))
    val ds_2 = rdd2.toDS()//.show(false)
    val df_2 = rdd2.toDF()
    println(ds_2.schema.fields.mkString(","))
    println(df_2.schema.fields.mkString(","))



//    StructField(_1,StringType,true),StructField(_2,IntegerType,true)
//    StructField(_1,StringType,true),StructField(_2,IntegerType,true)
//    =========================='
//    StructField(t_name,StringType,true),StructField(t_age,LongType,true),StructField(t_sex,StringType,true)
//    StructField(t_name,StringType,true),StructField(t_age,LongType,true),StructField(t_sex,StringType,true)
//

    ds_2.filter($"t_age" > 0).show(false)//2中都可以用
    ds_2.filter(_.t_age>0).show(false)//只能是ds

//    ds_2.filter(col("t_age")).show(false ) : 这个测不出来
    // udf(): 很多参数是怎么回事
  }
}
case class People2(t_name:String ,t_age:Long,t_sex:String)