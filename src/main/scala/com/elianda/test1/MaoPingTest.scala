package com.elianda.test1

import com.elianda.test5.User3
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
case class Usertest(name:String,age:Int,add:String,etl_date:String)

object MaoPingTest {

  def main(args: Array[String]): Unit = {

   val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
   val sc = new SparkContext(conf)
   sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val rdd01 = sc.parallelize(Array(
      Usertest("001",1,"西安1","20180101"),
      Usertest("002",1,"深圳1","20180101"),
      Usertest("003",1,"上海1","20180101"),
      Usertest("004",1,"广州1","20180101"),
      Usertest("005",1,"北京1","20180101")),10)

    val rdd02 = sc.parallelize(Array(
      Usertest("001",2,null,"20180101"),
      Usertest("002",2,"深圳2","20180101"),
      Usertest("003",2,null,"20180101"),
      Usertest("004",2,"广州2","20180101"),
      Usertest("005",2,"北京2","20180101")),5)
  //rdd转df
    val info_01 = rdd01.toDF.alias("yes")
    val info_02 = rdd02.toDF.alias("today")

    info_02.show(100)

    //毛老师： 精确复现
    val selects = info_02.schema.map(_.name.toUpperCase).diff(Seq("name").map(_.toUpperCase))
      .map(fileName=>coalesce(col("today."+fileName),col("yes."+fileName)).alias(fileName))  ++ Seq("name").map(col)

    val test_ds = info_01.join(info_02,Seq("name"),"left")
      .select(selects:_*).orderBy("name").coalesce(5)
    test_ds.show(100)

    Thread.sleep(10000000)


/*    //要精确： 模拟太粗糙, 空值与空传
    val selects2= Seq(col("name"),col("add"),coalesce(col("add"), col("name")) as "test" )

    //只是对空值进行判断,when也是可以的
    val selects1= Seq(col("name"),col("add"),when(col("add").isNull,col("name")).otherwise(col("add")) as "test" )

    info_02.select(selects1:_*).show()//coalesce的用法*/


  }
}
