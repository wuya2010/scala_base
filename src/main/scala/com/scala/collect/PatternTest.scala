package com.scala.collect

import scala.io.StdIn

/**
  * @author kylinWang
  * @data 2018/12/23 23:54
  *
  */
object PatternTest {
  def main(args: Array[String]): Unit = {

    test2
  }


  //直接怼项目，代码能很熟练的进行，一定要如此
  def test1={
    val arr = Array(10, 20, 30, 20, 20)
    val str = arr match {
      case Array(10) => "牛逼"
      case _ => "88"
    }
    println(str)//10
  }


  //写一个简单的计算机
  def test2= {
    val a = 1
    val b = 3
    var isFlag = true

   do{
        val op = StdIn.readLine("请输入一个运算符: ")
        //进行一个匹配
        val opTest = op match {
          case "+" => a + b
          case "-" => a - b
          case "*" => a * b
          case _ => a
          }

        println(opTest)

        if(op.equals("over"))
         isFlag = false
        }while (isFlag)

  }


  def testList ={
    val list = List(1, 2, 3,4)



  }


}
