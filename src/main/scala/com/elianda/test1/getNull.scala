package com.elianda.test1

import scala.xml.Null

/**
  * @author kylinWang
  * @data 2018/12/23 11:43
  *
  */
object getNull {

  def main(args: Array[String]): Unit = {

    val arr = Array(1,2,3)
    val arr1 = arr :+ Null
    arr.foreach(println(_))
    arr1.foreach(println(_))

    println("=================")
    //利用scala代码，基本方法
  val str = "2110"
    try {
      val t = Integer.parseInt(str)
      val t2 = str.toInt
      val t3 = t2 % 0
      println(t)
      println(t2)
      println(t3)
    } catch {
      case e :Exception =>  {
        if(e.getMessage.contains(""))
          println("you are wrong")
      }
        e.printStackTrace()
    }


  }

}
