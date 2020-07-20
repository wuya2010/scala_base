package com.scala.function

/**
  * @author kylinWang
  * @data 2018/12/20 0:16
  *
  */
object PrimeNumber {

  def main(args: Array[String]): Unit = {

    //求1-100内质数的和
    var sum = 0
    for(i <- 1 to 100){
        if(isPrime(i))  sum +=i
    }
    println(sum)
  }

  //如果返回的true,则调用这个函数
  def isPrime(n:Int):Boolean={

    for(i <- 2 until n){
      if( n % i == 0) false
    }
    true
  }
}
