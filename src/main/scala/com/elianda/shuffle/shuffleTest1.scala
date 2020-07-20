package com.elianda.shuffle

import java.util.Random

import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/1/16 15:12
  *
  */
object shuffleTest1 {
  def main(args: Array[String]): Unit = {
      val number = 100000
      val defpar = 100
    val skewPart = 7

    val spark = SparkSession.builder().master("local[5]")
      .config("spark.default.parallelism", defpar).getOrCreate()

   val data =  for(num <-0 to number) yield (if(num<defpar) num else number+(new Random()).nextInt(defpar)*(skewPart),1)
    data.foreach(print(_))
    //情况1 ： groupByKey后，数据根据hash进行分区，都集中在1个分区中
//    val tt = spark.createDataFrame(data).rdd.map(row => (row(0), row(1))).groupByKey(skewPart).count()  //groupByKey(skewPart):可以重置重分区数

    //情况2： 分区 ： 重分区可以将数据倾斜的字段打散
    val tt = spark.createDataFrame(data).rdd.map(row => (row(0), row(1))).groupByKey
      .repartition(200).count()

    Thread.sleep(1000000)

spark.close()

  }

}
