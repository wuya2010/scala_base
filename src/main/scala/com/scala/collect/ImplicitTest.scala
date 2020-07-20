package com.scala.collect

/**
  * @author kylinWang
  * @data 2018/12/23 23:47
  *
  */
object ImplicitTest {

  //这个确实不是很清楚的
  def main(args: Array[String]): Unit = {

    test

  }

  def test ={

    //包装类与基本类的转换
    implicit def double2Int(d:Double)=d.toInt
    val n = 10.3
   // 输出10.3
    println(n)

  }

}
