//package com.spark.test2
//
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
///**
//  * @author kylinWang
//  * @data 2018/6/28 21:45
//  *
//  */
//object spark_kafka {
//
//  def main(args: Array[String]): Unit = {
//    /*
//        利用 kafka 建立流式数据
//     */
//    val conf = new SparkConf().setMaster("local[*]").setAppName("streaming")
//    val ssc = new StreamingContext(conf,Seconds(10))
//
//
//
//
//
//  }
//
//  //kafak方法1
//  def kafkaStreaming_1(conf:SparkConf, ssc:StreamingContext)={
//    //kafka参数
//    val brokers = "hadoop105:9092,hadoop106:9092,hadoop107:9092"
//    val topic="first"
//    val group = "bigdata"
//    //kafkaPrmas
//    val kafkaPrams = Map(
//      ConsumerConfig.GROUP_ID_CONFIG -> group,
//      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers
//    )
//
//
//
//  }
//
//
//  //kafka方法2
//  def kafkaStreaming_2(conf:SparkConf,ssc:StreamingContext)={
//
//    ssc.checkpoint("./kafka")
//    //kafka参数
//    val brokers = "hadoop105:9092,hadoop106:9092,hadoop107:9092"
//    val topic="first"
//    val group = "bigdata"
//    //kafka
//    val kafkaPrams = Map(
//      ConsumerConfig.GROUP_ID_CONFIG -> group,
//      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers
//    )
//
//    kafkaUtils.c
//  }
//
//
//  //kafka3
//  def kafkaStreaming_3(conf:SparkConf,ssc:StreamingContext)= {
//
//    val brokers = "hadoop100:9092,hadoop202:9092,hadoop203:9092"
//    val topic = "first"
//    val group = "bigdata"
//    val kafkaParams = Map(
//      ConsumerConfig.GROUP_ID_CONFIG -> group,
//      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers
//    )
//
//    //手动提交offset
//
//
//
//
//
//
//
//
//  }
//
//}
