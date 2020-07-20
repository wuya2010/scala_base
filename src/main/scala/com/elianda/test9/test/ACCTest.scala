package com.elianda.test9.test

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/3/6 19:54
  *
  * 累加器没有说明清楚，，，，这个要注意
  *
  */
object ACCTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]").setAppName("xxx")

    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(Array(3,5,1,2),2)

    val acc = new MyAcc
    //注册累加器
    sc.register(acc)
    val rdd2 = rdd.map(x => {
      acc.add(1)
      (x, 1)
    })

    rdd2.collect().foreach(println(_))
    println("================")
    println(acc.value)




  }

}
