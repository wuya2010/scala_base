package com.base.list

import scala.collection.mutable.ListBuffer

/**
  * Author kylin
  * Date 2018-09-07 10:31
  */
object ListDemo {
    def main(args: Array[String]): Unit = {
        val list1 = List(10, 20, 30, 40)
        val list2 = List(100, 200, 300, 400)
        //        val list2 = list1 :+ 100
        //        val list2: List[Int] = 100 +: list1
        //        val list2: List[Int] = 200 :: 100 :: list1    //结果 List(200, 100, 10, 20, 30, 40)
        //        val list3 = list1 ++ list2
        //        val list3 = list1 ::: list2  // list2.:::()  //List(10, 20, 30, 40, 100, 200, 300, 400, 100)

        //        println(Nil)   // List[Nothing]()
        val list3 = 1 :: 2 :: 3 :: Nil
        println(list3)

//        val lb = ListBuffer[Int]()
    }
}

/*
List也分两种: 可变和不可变

List  不可变

    :+  +: 很少使用
    不可变List 专用:  ::  :::

ListBuffer 可变




 */