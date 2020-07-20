package com.elianda.test6

import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/1/6 20:32
  *
  */
object testParitition3 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]")
      .config("spark.sql.shuffle.partitions",100)
      .config("spark.default.parallelism",150)
      .appName("xxx").getOrCreate()
    val sc = spark.sparkContext

    val data = Array(("001",2,"西安2","20180102"),("002",2,"深圳2","20180102"),("002",2,"深圳2","20180102"))
    val data2 = Array(("001",1,"西安1","20180101") ,("002",1,"深圳1","20180101"),("003",1,"上海1","20180101"),("004",1,"广州1","20180101"),("005",1,"北京1","20180101"))

    val df = spark.createDataFrame(data).toDF("id","num", "location", "year")//.repartition(3)
    df.show()
    val dfNum = df.rdd.getNumPartitions
    print("df的分区数是" + dfNum)
    val df2 = spark.createDataFrame(data2).toDF("id","num","location","year")//.repartition(1)
    val df2Num = df2.rdd.getNumPartitions
    print("df2的分区数是" + df2Num)

    val dfCombine = df2.join(df,Seq("id"),"left")//.repartition(4)
    println(dfCombine.rdd.toDebugString)
    val dfCombineNum = dfCombine.rdd.getNumPartitions
    print("dfCombine的分区数是" + dfCombineNum)

  }
}
