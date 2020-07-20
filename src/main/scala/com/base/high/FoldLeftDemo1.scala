package com.base.high

import scala.collection.mutable

/**
  * Author kylin
  * Date 2018-09-07 15:39
  */
object FoldLeftDemo1 {
    def main(args: Array[String]): Unit = {

        val list2 = List(30, 50, 70, 60, 10, 20)
        println(list2.foldLeft(100)(_ + _))





        // hello -> 4  world->1 ,,
        val list1: List[String] = List("hello world", "hello hello", "atguigu atguigu hello")



        //  输出：  List(hello, world, hello, hello, atguigu, atguigu, hello)
        val words: List[String] = list1.flatMap(_.split(" "))

//List(hello, world, hello, hello, atguigu, atguigu, hello)
//println(words)



        //  以下不太明白:

        // 使用不可变map
        val map: Map[String, Int] = words.foldLeft(Map[String, Int]())((map, word) => {
            val value: Int = map.getOrElse(word, 0) + 1
            map + (word -> value)
        })

        println(map)   //Map(hello -> 4, world -> 1, atguigu -> 2)


        // 使用可变map   可变的map
        val map2 = words.foldLeft(mutable.Map[String, Int]())((map, word) => {
            val value: Int = map.getOrElse(word, 0) + 1
            map + (word -> value)
        })
        println(map2)
        
    }
}

/*
折叠:  fold
foldLeft

 */