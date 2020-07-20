package com.scala.collect

import scala.collection.mutable

/**
  * @author kylinWang
  * @data 2018/12/23 0:24
  *
  */
object ListAndMap {

  def main(args: Array[String]): Unit = {

    testmap5
  }

  //关于空； 定义一个方法
  def testlist={
    val list3 = 1::2::3::Nil
    println(list3)
  }

  //每一个基础的模型都要运行多遍
  def  testmap ={
    val map = Map("1"->1,"2"->2,"3"->3)
    map.foreach(println(_))
  }

  def testmap2={
   val map = Map("a" -> 97, "b" -> 98, "d" -> 100, "c" -> 99, "d" -> 99)

    /*  定义的def方法
    def apply(key: A): B = get(key) match {
      case None => default(key)
      case Some(value) => value
    }*/

     val getKey = map.apply("a")
    println(getKey)

    val getKey2 = map.getOrElse("f",100)
    //如果美哟给默认值100
    println("===="+getKey2)
  }


  def test3={
    val map = Map("a" -> 97, "b" -> 98, "d" -> 100, "c" -> 99, "d" -> 99)

  //获取map的方式
    for(kv <- map){
      println(kv._1,kv._2)
    }

    println("==================")
    for((k,v)<- map if v < 98){
      println(k,v)
    }
  }


  def testmap4 ={
  val map = mutable.Map(("1",1),("2",2),"3"->3)
  println(map)
  }


  def testmap5 ={
    var map = mutable.Map("a" -> 97, "b" -> 98, "d" -> 100, "c" -> 99, "d" -> 99)
    //1684890795
    println(System.identityHashCode())

    map += "5"->5
    //Map(5 -> 5, b -> 98, d -> 99, a -> 97, c -> 99)
    println(map)

    //1684890795 :不发生改变
    println(System.identityHashCode())
  }

}
