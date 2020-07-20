package com.elianda.test5

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/12/31 18:59
  *  这个没测完成
  */
object broadTest {

  def main(args: Array[String]): Unit = {
    val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

   val rdd = sc.parallelize(Array("1","2","3","4","5"))

    //广播变量的设置
    val set = Set[String]("2")

    //如何去读
    rdd.foreach(x=>println(x.contains(set)))

  }

}
