package com.implicited

import java.time.LocalDate
/**
  * @author kylinWang
  * @data 2018/7/19 19:11
  *
  */
object implictTest3 {
    def main(args: Array[String]): Unit = {
        
        val ago = "ago"
        val later = "later"
        
        println(10 days ago)
        println(2 days later)
        
        //
    }
    
    
    implicit class RichTime(day: Int) {
        def days(when: String): String = {
            if (when == "ago") {
                LocalDate.now().minusDays(day).toString
                
            } else {
                LocalDate.now().plusDays(day).toString
            }
        }
    }
    
}


/*
隐式转换:
    隐式转换函数
       implicit def double2Int(d: Double): Int = d.toInt
    
    隐式类
        其实是隐式转换函数的语法糖
    
    隐式参数和隐式值
        他们配合使用
 */