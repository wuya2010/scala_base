package com.elianda.test1

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object getDS_filter {

  def main(args: Array[String]): Unit = {

   val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
   val sc = new SparkContext(conf)
   sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import org.apache.spark.sql.functions._
    import spark.implicits._

    val rdd = sc.parallelize(Array(User("000lisi",20,"北京"),User("111wangwu1",25,"深圳"),User("000lisi",22,"上海"),User("412wangwu2",23,"北京"),User(",,,wangwu1",20,"深圳")))

  //rdd转df
    val getds = rdd.toDS()

    //使用filter
    val new3 = getds.filter(col("add")==="北京"||col("add")==="深圳")

    //提交的过程
   // val new4 = getds.select(ltrim(col("name"),"0-9"))

    new3.show(false)
  
    sc.stop()
  }
}
