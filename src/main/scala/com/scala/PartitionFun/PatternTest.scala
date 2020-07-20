package com.scala.PartitionFun

import scala.io.StdIn

/**
  * @author kylinWang
  * @data 2018/1/13 13:05
  *
  */
object PatternTest {
  def main(args: Array[String]): Unit = {

    test7

  }

  def test1 = {
    val testArray = Array(1, 2, 3)

    testArray match {
      //case Array(1,_,_)=> println("1")
      case Array(1) => println("2")
      case _ => println("牛逼")
    }
  }

  def test2 = {
    val a = StdIn.readInt()
    val b = StdIn.readInt()
    val connect = StdIn.readLine()

    connect match {
      case "+" => println(a + b)
      case _ => println("xxx")

    }
  }

  def test3 = {
    val list = List(1, 2, 3, 4)
    list match {
      case a :: b :: c => println(a, b, c) //(1,2,List(3, 4))
    }
  }


  def test4 = {
    val t = (1, "tt", 5)

    t match {
      case (a, b, c) => println(a)
      case (1, _, c) => println(c)
    }
  }


  def test5 = {

    val s = List(1, 2, "a", "abc", "+", "-", true)

    s.foreach(t => {
      t match {
        case a: Int => println(a + 2)
        case b: String if b == "+" || b == "-" => println(1)
        case _ => "xx"
      }
    })
  }


  def test6 = {
     val mapTest = Map((1 -> 2, 2 -> 3))

     mapTest.foreach(println(_))
  }



  def test7={
    val map = Map(1 -> (2, 20), 10 -> (20, 30), 20 -> (30, 40))

    val new_map = map.map{
      case (k,(_,v))=>(k,v)
    }

    println(new_map)

  }


}


