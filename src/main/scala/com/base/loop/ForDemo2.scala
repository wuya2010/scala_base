package com.base.loop

import scala.collection.immutable

/**
  * Author kylin
  * Date 2018-09-04 09:01
  */
object ForDemo2 {
    def main(args: Array[String]): Unit = {
        
        // for 推导  (很少使用)
        val arr: immutable.IndexedSeq[Int] = for (i <- 1 to 10) yield i * i * i
        println(arr)
    
        println((1 to 10).map(_ + 3))
    }
}
