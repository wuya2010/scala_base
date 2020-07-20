package com.elianda.test5

import com.elianda.test1.User
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/12/31 15:36
  *
  */
object CacheTest {

  def main(args: Array[String]): Unit = {

    val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    sc.setCheckpointDir("ch1")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    val rdd = sc.parallelize(Array(User("lisi1",12,"北京"),User("wangwu",15,"深圳"),User("lisi0",15,"深圳"),
      User("lisi0",12,"深圳"),User("lisi3",20,"北京")))

    val rdd2 = rdd.map(str=>(str.name,1)).reduceByKey(_+_)

    //怎么获取对应文件的地址

    rdd2.collect().foreach(println(_))
    //rdd.checkpoint()
    rdd2.cache()
    rdd2.collect().foreach(println(_))

    Thread.sleep(10000000)


  }
}
