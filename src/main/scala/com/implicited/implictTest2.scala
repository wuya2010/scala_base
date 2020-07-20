package com.implicited

import java.io.File

import scala.io.Source

/**
  * @author kylinWang
  * @data 2018/7/19 19:27
  *  隐式转
  */
object implictTest2 {

  def main(args: Array[String]): Unit = {
    //todo： 隐式转换条件 ---1. 返回值相同  2. 传入参数一致
    implicit def file2RichFile(file:File) = new RichFile(file)

    //隐式方法
    val file2 = new File("").readFile
  }
}

//todo
class RichFile(file:File){
  def readFile=
        Source.fromFile(file).mkString  //BufferSource to String
}
