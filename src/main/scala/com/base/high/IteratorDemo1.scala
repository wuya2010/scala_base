package com.base.high

/**
  * Author kylin
  * Date 2018-09-07 16:36
  */
object IteratorDemo1 {
    def main(args: Array[String]): Unit = {
        val it = "abvdsfja".toIterator.toList


        //输出： List(a, b, v, d, s, f, j, a)
        println(it)

        // 输出： 一个字符一行
       it.foreach(println)
        /*println("----------")
        it.foreach(println(_))
        
    }*/
        
        val list1 = List(30, 50, 70, 60, 10, 20)
//        val list2 = list1.map(x =>x + 1)

//        list1.foreach(println(_ ))
//        println(list2)
    }
}
