package com.elianda.shuffle

import com.elianda.test1.Usertest
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/1/6 15:06
  *
  */


//针对2个表的join进行测试，最终的分区结果
object shuffleTest3 {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]")
      .config("spark.sql.shuffle.partitions", 100).appName("xxx").getOrCreate()
    val sc = spark.sparkContext

    sc.setLogLevel("WARN")

    val rdd1 = sc.parallelize(Array(
      Usertest("", 1, "西安1", "20180101"),
      Usertest("", 1, "深圳1", "20180101"),
      Usertest("", 1, "上海1", "20180101"),
      Usertest("", 1, "广州1", "20180101"),
      Usertest("", 1, "北京1", "20180101")))

    val rdd2 = sc.parallelize(Array(
      Usertest2("001", 2, "西安2", "20180102"),
      Usertest2("001", 2, "深圳2", "20180102"),
      Usertest2("001", 2, "深圳2", "20180102")))

    import spark.implicits._
    val df1 = rdd1.toDF()
    val df2 = rdd2.toDF()

    println("================A表主键为''，B表匹配=============================")
  //情况1： A leftjoin   B ： A主键全部为空： 匹配为空，B表值为空
    val df12 = df1.join(df2, Seq("name"), "left")
//    println(df3.rdd.toDebugString)
    println("=====A表======")
    df1.show(false)
    println("=====B表======")
    df2.show(false)
    println("=====结果======")
    df12.show(false)
    println(df12.count())

    println("================A表/B表：null字段为主键=============================")

    //情况2：  A leftjoin   B ： A主键全部相等，没有产生笛卡尔积
    val rdd3 = sc.parallelize(Array(
      Usertest2("", 2, "西安2", "20180102"),
      Usertest2("", 2, "深圳2", "20180102"),
      Usertest2("", 2, "深圳2", "20180102")))

    val df3 = rdd3.toDF()

    val df13 = df1.join(df3, Seq("name"), "left")
//    println(df5.rdd.toDebugString)
    println("=====A表======")
    df1.show(false)
    println("=====B表======")
    df3.show(false)
    println("=====结果======")
    df13.show(false)
    println(df13.count())

    println("=================A，B表主键相同============================")

    val rdd7 = sc.parallelize(Array(
      Usertest("001", 1, "西安1", "20180101"),
      Usertest("001", 1, "深圳1", "20180101"),
      Usertest("001", 1, "上海1", "20180101"),
      Usertest("001", 1, "广州1", "20180101"),
      Usertest("001", 1, "北京1", "20180101")))

    val df7 = rdd7.toDF()

    println("=====A表======")
    df7.show(false)
    println("=====B表======")
    df2.show(false)
    println("=====结果======")

    val df72 = df7.join(df2, Seq("name"), "left")
    //    println(df5.rdd.toDebugString)
    df72.show(false)
    println(df72.count())
  }
}

case class Usertest2(name:String,age2:Int,add2:String,etl_date2:String)