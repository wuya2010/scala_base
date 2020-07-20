package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 16:12
  */
object PatterUse {
    def main(args: Array[String]): Unit = {
        /*val arr = Array(10, 20, 30)
        
        val Array(_, a, b) = arr

    //输出a与b 的值
        println(a)
        println(b)

        //底层还是匹配
        */


        //模式匹配的使用场景
        /*val (_, t): (BigInt, BigInt) = BigInt(10) /% 3
        println(t)*/
        
        
        val map = Map(1 -> 2, 2-> 3)
        for ((k, v) <- map) {
            println(v)
        }
    }
}
/*
Array
List
Tuple
 */