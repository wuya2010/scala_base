package com.base.pattern

import scala.io.StdIn

/**
  * Author kylin
  * Date 2018-09-09 14:46
  */
object PatternValVar {

    //可以匹配常量，也可以匹配变量
    def main(args: Array[String]): Unit = {
        val a = 1
        val b = 2
        val op = StdIn.readLine("请输入一个运算符: ")
        val Plus = "+"
        op match {

            //这里的plus与外面的plus没有任何关系，要设置常量，需要首字符大写
            case Plus => println(a + b)
            case "-" => println(a - b)
            case "*" => println(a * b)
            case "/" => println(a / b)
            
        }
        
    }
}

/*
模式匹配:

变量匹配:
 case aa => println(aa)  匹配所有的值
 case _ =>
 
常量匹配:
    常量的名字的首字母必须大写

*/