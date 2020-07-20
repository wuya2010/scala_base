package com.base.newwork

/**
  * Author kylin
  * Date 2018-09-07 08:17
  */
object Newwok1 {
    def main(args: Array[String]): Unit = {
    
    }
}

class Point(val x: Double, val y: Double)

object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
}

/*
1. 定义一个 Point 类和一个伴生对象,使得我们可以不用 new 而直接用 Point(3,4)来构造 Point 实例
 */
