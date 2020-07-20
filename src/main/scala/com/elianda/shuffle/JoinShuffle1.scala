package com.elianda.shuffle

import java.util.Random

import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/1/16 19:29
  *
  */
object JoinShuffle1 {
  def main(args: Array[String]): Unit = {
    val number = 6000000
    val sml = 60000
    val defpar = 100
    val skewPart = 7

    val data1 =  for(num <-0 to number) yield (if(num<defpar) num else number+(new Random()).nextInt(defpar)*(skewPart),1)
    val data2 =  for(num <-0 to sml) yield (if(num<defpar) num else number+(new Random()).nextInt(defpar)*(skewPart),1)

    val spark = SparkSession.builder().master("local[5]")
      .config("spark.default.parallelism", defpar)
    // .config("spark.sql.autoBroadcastJoinThreshold","1")//1bit : 广播变量值太小
     .config("spark.sql.autoBroadcastJoinThreshold",s"${100L*1024*1024}")//广播变量值扩大为： 100M
      .config("spark.sql.shuffle.parttiions",skewPart)
      .getOrCreate()

    //创建DF
    val dfbig = spark.createDataFrame(data1).toDF("id","value")
    val dfsmall = spark.createDataFrame(data2).toDF("id","value")

    val joinDf = dfsmall.join(dfbig,Seq("id"),"left").count() //小表join大表

//    println(joinDf.count())

    Thread.sleep(1000000000L)
    spark.close()


  }

}
