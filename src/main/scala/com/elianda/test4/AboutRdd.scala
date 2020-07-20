package com.elianda.test4

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/12/24 14:53
  *
  */
object Newpartitoner {
/*  val conf =  new SparkConf().setAppName("Practice").setMaster("local[2]")

  val sc = new SparkContext(conf)

  val rdd1 = sc.parallelize(Array(1,2,3,),2).map((_,1))

  //生成一个partiton对象
 val newpartitoner = new Newpartitoner(2)

 val t =  rdd1.partitionBy(new Newpartitoner(2)).map(_._1)
}

class Newpartitoner(num:Int) extends Partitioner {

  override def numPartitions: Int = num

  override def getPartition(key: Any): Int = {

  }*/
}
