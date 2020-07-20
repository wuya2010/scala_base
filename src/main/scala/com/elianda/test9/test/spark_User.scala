package com.elianda.test9.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/3/6 14:44
  *
  */
object spark_User {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]").setAppName("xxx").set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .registerKryoClasses(Array(classOf[Searcher]))

    val sc = new SparkContext(conf)

    sc.setLogLevel("ERROR")

    val rdd = sc.textFile("E:\\01_myselfProject\\spark_all_project\\spark_streaming\\src\\main\\agent.log")

   val rdd2 = rdd.flatMap(_.split("\\s")).map((_,1)).reduceByKey(_+_)

    rdd2.collect().foreach(println(_))

  val searcher = new Searcher("hello")
    searcher.isMatch("rdd")



  }


//  def test1: Unit ={
//    val conf = new SparkConf()
//    conf.setMaster("local[*]").setAppName("xxx")
//
//    val sc = new SparkContext(conf)
//
//    sc.setLogLevel("ERROR")
//
//    val rdd = sc.textFile("E:\\01_myselfProject\\spark_all_project\\spark_streaming\\src\\main\\agent.log")
//
//    val rdd2 = rdd.map(_.split("\\s"))
//
//    rdd2.collect().foreach(str=>println(str.mkString(";")))
//
//    sc.stop()

//  1516609241720;1;1;45;11
//  1516609241720;4;6;98;15

}

//定义类没有=
class Searcher(query:String) extends Serializable{

  def isMatch(s: String)={
    s.contains(query)
  }

  def getMatchRdd(rdd:RDD[String])={
    rdd.filter(isMatch)
  }



}