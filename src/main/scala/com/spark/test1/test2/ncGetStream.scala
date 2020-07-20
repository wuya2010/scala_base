package com.spark.test1.test2

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author kylinWang
  * @data 2018/6/4 11:25
  *
  */
object ncGetStream {
  def main(args: Array[String]): Unit = {

    /*
        根据nc获取流式数据

     */
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val ssc = new StreamingContext(conf, Seconds(6))

    val stream = ssc.fileStream("")

    val socketStream = ssc.socketTextStream("",9999)

    val test = socketStream.flatMap(_.split("\\S")).map((_,1)).reduceByKey(_+_)

    test.print()
    ssc.start()
    ssc.awaitTermination()

  }

}
