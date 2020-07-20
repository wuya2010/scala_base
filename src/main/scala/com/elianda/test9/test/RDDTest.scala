package com.elianda.test9.test

import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/3/6 22:47
  *
  */
object RDDTest {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]")
      .appName("xx").getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val rdd = spark.sparkContext.textFile("E:\\01_myselfProject\\spark_all_project\\spark_streaming\\src\\main\\agent.log")
    import spark.implicits._
//    rdd.collect().foreach(println(_))

//    1516609241720 1 3 50 6
//    1516609241720 1 1 45 11
//    1516609241720 4 6 98 15

    val rdd2 = rdd.map(str=>str.split("\\s")(0))
//    rdd2.collect().foreach(println(_))

//    println(rdd.toDF().schema.fields.mkString(","))
rdd.toDF().show(false)


//    +-----------------------+
//    |value                  |
//    +-----------------------+
//    |1516609143867 6 7 64 16|
//      |1516609143869 9 4 75 18|
//      |1516609143869 1 7 87 12|

  }

}
