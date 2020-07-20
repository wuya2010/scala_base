package com.scala.PartitionFun

import org.apache.spark.sql.functions.{col, lit, when}

/**
  * @author kylinWang
  * @data 2018/1/13 9:47
  *
  */
object ExceptionTest {
  def main(args: Array[String]): Unit = {

    try{

      throw new IllegalArgumentException("xx")
    }catch {
      case e : ArithmeticException => println(e)
      case e : IllegalArgumentException => println(e)
      case _ =>
    }finally {
      println("出问题")
    }







  }


}
