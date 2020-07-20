package com.elianda.test9.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/3/6 19:54
  *
  */
object BroadTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(3,5,1,2))

    val bd = sc.broadcast(Set(3,5))

    rdd.foreach(x=>{
      val t = bd.value//获取广播变量的值
      println(t.contains(x))  //返回true / false
    })

    rdd.foreach(println(_))//每一个元素都打印出来




  }

}
