package com.scala.grammer

import org.slf4j.LoggerFactory

/**
  * @author kylinWang
  * @data 2018/12/19 23:50
  *
  */
object typeTest {

  def main(args: Array[String]): Unit = {

    getTyep

    getVar("王琦琳",20)

  }


//定义变量
  def defineVar={
    val s  = ""
    val a = foo()

  }
    def foo() = ???

  //自动类型提升
  def getTyep={
    val a:Int = 10.5.toByte
    println(a)
  }

  //定义变量
  def getVar(name:String,age:Int = 10)={

    val logger = LoggerFactory.getLogger(this.getClass)

    logger.info(s"我的名字是${name}, 年纪${age+2}")

    println(s"我的名字是${name}, 年纪${age+2}")

    val s =
      s"""
        |ni
        |shi
        |shui=${name}
      """.stripMargin

    println(s)
  }


}
