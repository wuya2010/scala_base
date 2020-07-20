package com.scala.PathTest

import com.typesafe.config.ConfigFactory

/**
  * @author kylinWang
  * @data 2018/7/16 19:03
  *
  */
object ClassLoad2 {

  def main(args: Array[String]): Unit = {

    //相对路径：相对与某个基准目录的路径

    //方式一： 可以实现 ： + "/" 默认从 classpath 读取数据
      val stream = this.getClass.getResourceAsStream("/oracleconn.properties")

    //E:\ChinaBank\first_project - test\src\main\scala\com\scala\PathTest\ClassLoad.scala
    //E:\ChinaBank\first_project - test\src\main\resources\oracleconn2.properties
    //E:\ChinaBank\first_project - test\src\main\scala\oracleconn2.properties


    //方式三：classload: 默认不加 "/"
    val stream2 = this.getClass.getClassLoader.getResourceAsStream("oracleconn.properties")


    //读取配置
    //ConfigFactory 工厂方法默认会读取 resources 目录下面名为 application.conf 的文件：
    val stream3 = ConfigFactory.load("oracleconn.properties")


      val num = stream.available()
      val bytes = new Array[Byte](num)
      var len  = 0
      while( len != -1){
        len = stream.read(bytes)
        println(len)
      }

  }


}
