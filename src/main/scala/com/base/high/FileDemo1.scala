package com.base.high

import scala.io.Source

/**
  * Author kylin
  * Date 2018-09-07 17:15
  */
object FileDemo1 {
    def main(args: Array[String]): Unit = {
        val path = "E:\\Learning\\05-SPARKS\\05-IDEA_Git\\Scala\\src\\main\\scala\\com\\atguigu\\scala0508\\day04\\high\\FileDemo1.scala"
        val lines: List[String] = Source.fromFile(path).getLines().toList
        
        println(lines)
    }
}
