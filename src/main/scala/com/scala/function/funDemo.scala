package com.scala.function

/**
  * @author kylinWang
  * @data 2018/12/20 0:03
  *
  */
object funDemo {

  def main(args: Array[String]): Unit = {
    //变量只输出一次
    foo3
    foo3
    println(foo2(3,4))
    println(foo2(3,4))

    println("=========================")

    println(add(1, 5, 6, 7))
    println(add(1 to 100 : _*))

  }

  //1. 定义函数和返回值
  def foo: Int ={
    return 1
  }

  // 2. 2个参数的值
  def foo2(a:Int,b:Int)={
      a+b
  }

  val foo3 = {
    println("你是王八蛋")
  }


  def add(arr:Int*)={
    //设置可变的变量
    var sum = 0
    for(i <- arr){
      sum += i
    }
    sum
  }

}
