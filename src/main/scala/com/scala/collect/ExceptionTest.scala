package com.scala.collect

/**
  * @author kylinWang
  * @data 2018/12/23 23:06
  *
  */
object ExceptionTest {

  def main(args: Array[String]): Unit = {
    try {
      //            val a = 1 / 0
      throw new IllegalArgumentException("你犯法了")
    }catch{
      case e: ArithmeticException => println(e)
      case e: RuntimeException => println(e)
      case e: Exception => println(e)
      case _ =>
    }finally {
      println("finally")
    }

    println("aaa")

    try{
      foo("abc")
    }catch {
      case e:NumberFormatException => println("字符串的格式不对")
    }
  }

  @throws(classOf[NumberFormatException])
  def foo(a: String)  = {
    a.toInt
  }

}
