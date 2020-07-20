package com.implicited

/**
  * @author kylinWang
  * @data 2018/7/19 19:11
  *
  */
object implictTest1 {
  def main(args: Array[String]): Unit = {

      //todo: 隐式转换条件
      implicit  def double2Int(d:Double):Int = d.toInt

      val n:Int = 10.3 //todo: 标注返回值类型 Int

      println(n)
  }
}
