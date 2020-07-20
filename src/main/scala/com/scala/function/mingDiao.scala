package com.scala.function

import spire.std.map

/**
  * @author kylinWang
  * @data 2018/12/20 0:45
  *
  */
object mingDiao {
  def main(args: Array[String]): Unit = {

    for(i <- 1 to 9){
      for(j <- 1 to i){
       print(s"$j * $i = ${i*j}\t")
        if(i==j) println()
      }
    }




  }

  //函数作为参数
  def f1(f:(Int,Int)=>Int)={
      f(2,4)
  }

  def foo()={
    1
  }

}
