package com.scala.collect

/**
  * @author kylinWang
  * @data 2018/12/23 0:05
  *
  */
object homeword {

  def main(args: Array[String]): Unit = {
  //  test2.toString
  val card: Array[String] = Array( "♣", "♦","♥", "♠")

    //4种颜色
  getString(card)

  getCol("♥")



  }

  def getString(card:Array[String])={
    card.foreach(str=>println(str))
  }

  //检测红色
  def getCol(str:String)={
    if(str.contains("♠")|| str.contains("♦")) println("红色") else  println("黑色")
  }

  // 作业1： 1. 定义一个 Point 类和一个伴生对象,使得我们可以不用 new 而直接用 Point(3,4)来构造 Point 实例
  def test1: Unit ={

/*
    //调用object就新生成
  //  Point.


    object Point{
      override def apply: Point = new Point()
    }

    class Point{

    }
*/


  }



  //2. 编写一个扑克牌只能有 4 种花色,让其 toString 方法分别返回♣,♦,♥,♠，并实现一个函数,检查某张牌的花色是否为红色
  def test2: Unit ={
    val card = Array( "♣", "♦","♥", "♠")

    //4种颜色
    def toString={
        card.foreach(str=>println(str))
    }

    //检测红色
    def getCol(str:String)={
        if(str.contains("♠")|| str.contains("♦")) println("红色") else  println("黑色")
    }
  }




}
