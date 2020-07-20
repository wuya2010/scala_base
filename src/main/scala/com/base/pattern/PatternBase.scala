package com.base.pattern

import scala.io.StdIn

/**
  * Author kylin
  * Date 2018-09-09 14:46
  */
object PatternBase {
    def main(args: Array[String]): Unit = {
        val a = 1
        val b = 2
        val op = StdIn.readLine("请输入一个运算符: ")


        //match的对象： op
       op match{
           case "+" => println(a + b)
           case "-" => println(a - b)
           case "*" => println(a * b)
           case "/" => println(a / b)

           //匹配所有
           case _ =>
       }
       
    }
}
/*
模式匹配:


*/