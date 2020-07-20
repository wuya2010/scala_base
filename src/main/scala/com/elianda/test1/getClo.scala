package com.elianda.test1

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession


//这个没跑出来
object getClo {

  def main(args: Array[String]): Unit = {

    def getData(spark: SparkSession)={

      val conf = new SparkConf().setAppName("xxx").setMaster("local[*]")
      val sc = new SparkContext(conf)

      sc.setLogLevel("WARN")

      val Sourcefield = sc.textFile("E:\\ChinaBank\\first_project - test\\src\\main\\resources\\source.txt")
       // .flatMap(_.split("\t")).map((_,1))


      Sourcefield.glom().collect()

      Sourcefield.collect.foreach(println)

    }
  }
}
