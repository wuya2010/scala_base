package com.base.queue

import scala.collection.mutable

/**
  * Author kylin
  * Date 2018-09-07 11:17
  */
object QueueDemo {
    def main(args: Array[String]): Unit = {
        val qu = mutable.Queue(10, 20, 30)
        
        qu.enqueue(100) // 入队
        val ele = qu.dequeue() // 出对
        println(qu)
        println(ele)
    }
}

/*

 */
