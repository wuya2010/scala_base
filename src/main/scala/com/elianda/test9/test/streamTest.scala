package com.elianda.test9.test

import org.apache.spark.SparkConf
import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.streaming.DataStreamReader
import org.apache.spark.sql.types._
import org.apache.spark.streaming
import org.apache.spark.streaming.{Seconds, StreamingContext}







/**
  * @author kylinWang
  * @data 2018/3/6 23:20
  *
  */
object streamTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("xxx").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(4))

//StreamingContext.getActiveOrCreate()

    val spark = SparkSession.builder().master("local[*]")
      .appName("xx").getOrCreate()

    import spark.implicits._

    val data= spark.readStream.format("orc").option("key","value").load

    data.writeStream.format("console").outputMode("append")

    data.asInstanceOf[String]

    //有几次导包，不争取
//    new  StructType(StructField("",StringType) :: StructField("age",IntegerType))
val schema = new StructType().add("name",StringType).add("age",LongType)

//    spark.readStream.schema()
val ds = data.as[String].flatMap(_.split("\\w+")).map(
  word => (word,word.reverse)
)



//    data.writeStream.outputMode("complete").  //foreachBatch： 这个拿不出来


val t =     data.writeStream.foreach(new ForeachWriter[Row] {

  override def open(partitionId: Long, version: Long): Boolean = ???

  override def process(value: Row): Unit = ???

  override def close(errorOrNull: Throwable): Unit = ???

})




    ssc.awaitTermination()
    spark.stop
  }
}
