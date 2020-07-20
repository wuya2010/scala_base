package com.elianda.test8

/**
  * @author kylinWang
  * @data 2018/5/26 14:49
  *
  */
object testMatch {
  def main(args: Array[String]): Unit = {
    val str = "A123456"
    val is_true = str.matches("\\A|a")//数字匹配0-9
//    println(is_true)

    val demo = System.setProperty("is_test", "true")
    println(System.getProperty("is_test"))


  }
}
