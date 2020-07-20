package com.base.arr

import scala.collection.mutable.ArrayBuffer

/**
  * Author kylin
  * Date 2018-09-07 09:26
  */
object ArrayBufferDemo1 {
    def main(args: Array[String]): Unit = {
        val buffer = new ArrayBuffer[Int]    //数组中放元素
//        buffer.append(10, 20)
        buffer += 10
        buffer += 100

        //增加元素
        buffer += (101, 200)
        
        buffer.insert(0, -1, -2)
        
        buffer.remove(0)  // 删除下标为0的元素
        println(buffer)
        buffer.remove(0, 2)  // 从下标为0开始删, 删2个
        
        println(buffer)
        buffer -= 100  // 删除第一个碰到的100
        println(buffer)
    }
}

/*

+= 一般用于可变集合. 也可以用于不可变???
-=  删除第一个碰到
 */