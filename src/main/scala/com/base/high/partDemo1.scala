package com.base.high

/**
  * Author kylin
  * Date 2018-09-07 16:44
  */
object partDemo1 {
    def main(args: Array[String]): Unit = {
//        println(math.pow(9, 2))
        val f: Double => Double = math.pow(_, 2)
        println(f(3))
        println(f(10))
        
        
    }
    
}
/*
部分应用函数:

 */