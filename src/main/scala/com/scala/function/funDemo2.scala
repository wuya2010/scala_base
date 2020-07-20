package com.scala.function

/**
  * @author kylinWang
  * @data 2018/12/20 0:36
  *
  */
object funDemo2 {

  def main(args: Array[String]): Unit = {

    println(add(1,5))
    println(add2(2)(5))

  }

  def add(a:Int,b:Int)=a+b
  def add2(a:Int)(b:Int)=a*b

}
