package com.scala.DF_DS_Test

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{LongType, StringType, StructType}

/**
  * @author kylinWang
  * @data 2018/1/15 20:11
  *
  */
object Df_terst {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("tt").getOrCreate()

    import spark.implicits._
    val user = new StructType().add("name",StringType)
      .add("age",LongType)

    val user_df = spark.readStream.schema(user).load("E:\\ChinaBank\\first_project - test\\src\\main\\resources\\test.json")

    user_df.show(false)

    val user_ds = user_df.as[user]

    user_ds.show(false)

    user_ds.writeStream.outputMode("append").format("console").start.awaitTermination()
  }

}


case class user(name:String,age:Long)