package com.scala.collect

import scala.collection.mutable.ListBuffer

/**
  * @author kylinWang
  * @data 2018/12/22 22:52
  *
  */

//这个十分重要，要熟练掌握
object HighFunc2 {

  def main(args: Array[String]): Unit = {

      test6
  }

  // 可变map的使用
  def test: Unit ={
    val words = List("nihao","wode","zuguo","zhongguo")
    //初始值
    // foldLeft: 左折叠
    val getMap = words.foldLeft(Map[String,Int]())((map,word)=>{
      val value  = map.getOrElse(word,0)+1
      map + (word -> value)
    })

    //Map(nihao -> 1, wode -> 1, zuguo -> 1, zhongguo -> 1)
    println(getMap)

  }


  def test2: Unit ={
    val list1 = List(30, 50, 70, 60, 10, 20)
    //val result: Int = (0 /: list1)(_ + _)  // foldLeft

    //提供一个初始值
    val result = (list1 :\ 10)(_ + _)
    println(result)
  }


  //测试println
  def test3={
    val list1 = List(30, 50, 70, 60, 10, 20)
    //隔行显示
    list1.foreach(println)
    println("===============")
    list1.foreach(data=>println(data))
  }



  //测试可变的list
  def test5: Unit ={
    val list1 = ListBuffer(30, 50, 7, 6, 1, 20)
    //返回值类型
    val list2: Map[Boolean, ListBuffer[Int]] = list1.groupBy(_ % 2 != 1 )

    // 对 true 或 false 进行分组
    // Map(false -> ListBuffer(7, 1), true -> ListBuffer(30, 50, 6, 20))
    println(list2)

    //word_count的测试
    val list3 = List("hello world", "hello hello", "atguigu atguigu hello")
    //不能写成2个，这里是一个值
    val list4 = list3.flatMap(_.split(" ")).groupBy(t=>t).map(kv=>{
            val len = kv._2.length
            (kv._1,len)
          })

    //方式二
    val list5 = list3.flatMap(_.split(" ")).groupBy(t=>t).map(kv=>kv._1->kv._2.length)


    //方式三: 返回的是一个map ： Map(world -> Map(world -> List(world))
   val list6 =  list3.flatMap(_.split(" ")).groupBy(word=>word).groupBy(_._1)


    println(list6)
}


  //每个元素用：分隔
  def test6: Unit ={
    val  arr = Array(1,2,3)
    arr.foreach(println)
    //1：2：3
    println(arr.mkString(":"))
  }



}
