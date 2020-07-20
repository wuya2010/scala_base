package com.elianda.test1

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/12/15 0:21
  *
  */
object getTypeString {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("xxx")
    val sc = new SparkContext(conf)
    val test = sc.textFile("C:\\Users\\TOM\\Desktop\\0.txt")
    val tag = test.map(_.split(","))//.map(f=

    tag.collect.foreach(data=>println(data.mkString("\n")))


  }

}
