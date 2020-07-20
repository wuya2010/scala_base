package com.elianda.test6

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/1/6 14:45
  *
  */
object testPartition {
  def main(args: Array[String]): Unit = {

    //查看分区数
    val conf = new SparkConf().setAppName("xxx").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(Array(1,5,6,3,8,10,5))
    val rdd2 = rdd1.map((_,null))

    //重新定义分区 , 定义分区字段
    val rdd3 = rdd2.partitionBy(new getTest(20)) //设置分区数是20 ，但是实际只有2个分区，其他空跑
    println("=============获取分区数==================="+rdd3.getNumPartitions)

    println(rdd3.glom().collect)
    rdd3.glom().collect.foreach(str=>println(str.mkString(";")))
    sc.stop()
  }

}


//定义一个类
class getTest(num:Int) extends Partitioner{
  override def hashCode(): Int = super.hashCode()

  override def equals(obj: Any): Boolean = super.equals(obj)

  override def numPartitions: Int = num

  //定义分区规则,根据奇偶分区，并设置分区数
  override def getPartition(key: Any): Int = {
    val key2 = key.asInstanceOf[Int]
    //返回值是key2的
    key2 % 2.abs
  }

}