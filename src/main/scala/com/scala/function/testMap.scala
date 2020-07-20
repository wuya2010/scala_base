package com.scala.function

/**
  * @author kylinWang
  * @data 2018/4/20 10:23
  *
  */
object testMap {
  def main(args: Array[String]): Unit = {

    val map = Map(1->"name",2->"age")

    val t = map.map{case (key,value)=>{
      (key+1,value)
    }}

    println(t)
  }
}
