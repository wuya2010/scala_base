package com.elianda.shuffle

import java.util.Random

import org.apache.spark.Partitioner
import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/1/16 15:12
  *
  */

//自定义分区
object shuffleTest2 {
  def main(args: Array[String]): Unit = {
      val number = 100000
      val defpar = 100
    val skewPart = 7

    val spark = SparkSession.builder().master("local[5]")
      .config("spark.default.parallelism", defpar).getOrCreate()

    //自定义分区: 内部类
    val customPart = new Partitioner {

      val partitions = 8
      override def numPartitions: Int = {
        return partitions
      }

      override def getPartition(key: Any): Int ={
        val partKey = key.asInstanceOf[Int]
        partKey%partitions //用key与分区数取模
      }
    }

   val data =  for(num <-0 to number) yield (if(num<defpar) num else number+(new Random()).nextInt(defpar)*(skewPart),1)

    //情况3: 根据
    val tt = spark.createDataFrame(data).rdd.map(row => (row(0), row(1)))
      .groupByKey(customPart).count()

    Thread.sleep(1000000)

    spark.close()

  }

}
