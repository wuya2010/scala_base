package com.scala.flodLeftFun

import scala.collection.mutable

/**
  * @author kylinWang
  * @data 2018/4/17 9:42
  *
  */
object foldLeftTest {

  def main(args: Array[String]): Unit = {

/*  生成默认值的初始值：

    val list = Array(1,2,3,4,5)
    val new_list = list.foldLeft(100)(_+_)
    println(new_list)
*/

    // 使用不可变map
    /*val map: Map[String, Int] = words.foldLeft(Map[String, Int]())((map, word) => {
        val value: Int = map.getOrElse(word, 0) + 1
        map + (word -> value)
    })*/


/*    // 使用可变map   可变的map
    val map = words.foldLeft(mutable.Map[String, Int]())((map, word) => {
      val value: Int = map.getOrElse(word, 0) + 1
      map + (word -> value)
    })
    println(map)
    */

    val arr:Array[String] = Array[String]("shen zhen","nihao shen")
    val words = arr.flatMap(_.split(" "))
//    val map  = Map[String,Int]()

    //隐式函数传什么
    val map = words.foldLeft(Map[String,Int]())((map,word)=>{
      val count = map.getOrElse(word,0)+1
      map + (word ->count)
    })

    println(map)
  }
}
