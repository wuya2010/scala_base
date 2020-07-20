package com.scala.pattern

/**
  * @author kylinWang
  * @data 2018/1/14 14:48
  *
  */
object OptionTest {

  def main(args: Array[String]): Unit = {

    val t = foo()
    t match{
      case Some(n) => println(n)
      case _ => "kong"
    }


  }

  def foo():Option[Int]={
    Some(10)
  }

}
