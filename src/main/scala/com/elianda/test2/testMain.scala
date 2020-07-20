package com.elianda.test2
import org.apache.spark.sql.functions._

/**
  * @author kylinWang
  * @data 2018/12/18 15:23
  *
  */
object testMain {
  def main(args: Array[String]): Unit = {

    val te = Array("nihao")
    println(te.length)
    //assert(te.length>2, "两天全量总数40条")

    require(te.length>0, "两天全量总数40条")


  }

}
