package com.elianda.test7

import com.elianda.test1.User
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.Upper
import org.apache.spark.sql.functions._
import spire.macros.Auto.scala

/**
  * @author kylinWang
  * @data 2018/2/14 11:58
  *
  */
object test_new_fun2 {

  def main(args: Array[String]): Unit = {
    val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    //0 ; 3/9=0.333
    val rdd = sc.parallelize(Array(test2(0,2),test2(6,9)))


    val getdf = rdd.toDF()
    getdf.show(false)
    getdf.select(
      when(col("v1")===0,lit("0").cast("double")).otherwise(round((col("v2")-col("v1"))/col("v2"),3)) as "test"
//      when(col("v1")===0,lit("0")).otherwise(round(lit(2.5555),3)) as "test"
    ).show(false)
//   when(col("Y4_VALUE")===0,lit(0)).otherwise(round(col("Y3_VALUE")-col("Y4_VALUE"))/col("Y4_VALUE"),3)
  }//)

}

case class test2(v1:Int,v2:Int)