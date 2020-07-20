package com.elianda.test3

/**
  * @author kylinWang
  * @data 2018/12/24 12:45
  *
  */
object ClosedTest {
  def main(args: Array[String]): Unit = {


/*    println(add(1, 2))
    println("============")
    println(add2(1)(2))
    println("============")
    println(add3(3)(1))*/

    val str = 1
    val str2 = 1
    println("str1 + str2 = " + add2(str)(str2))

  }

  def add(a:Int,b:Int)={
    a+b
  }

  def add2(a:Int)(b:Int)={
    a+b
  }

  //接受一个参数，返回一个匿名函数
  def add3(x:Int)=(y:Int)=>x+y

}
