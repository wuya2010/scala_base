package com.base.map

import scala.collection.mutable

/**
  * Author kylin
  * Date 2018-09-07 11:27
  */
object MapDemo3 {
    def main(args: Array[String]): Unit = {
        var map = mutable.Map("a" -> 97, "b" -> 98, "d" -> 100, "c" -> 99, "d" -> 99)
//        map += (("z", 108))
        println(System.identityHashCode(map))

        map += "z" -> 108
        //获取地址值
        println(System.identityHashCode(map))

        //增加一个元素z显示
        println(map)
        map.getOrElseUpdate("x", 200)  // 只能可变
        println(map)
    }
}

/*


 */