package com.base.high

/**
  * Author kylin
  * Date 2018-09-07 15:39
  */
object FoldLeftDemo2 {
    def main(args: Array[String]): Unit = {
        val list1 = List(30, 50, 70, 60, 10, 20)
//        val result: Int = (0 /: list1)(_ + _)  // foldLeft

          //提供一个初始值
        val result = (list1 :\ 10)(_ + _)
        println(result)
    }
}

/*
折叠:  fold
foldLeft

 */