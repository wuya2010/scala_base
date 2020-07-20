package com.scala.collect

import scala.collection.mutable.ArrayBuffer


/**
  * @author kylinWang
  * @data 2018/12/20 23:33
  *
  */


object  arrTest {

  def main(args: Array[String]): Unit = {

    test0
    println("=============")
    test1
    println("======t2======")
    test2
    println("======t3======")
    test3
    println("======t5======")
    test5
  }

  //1. arr的增删改; 2种定义方式
  def test0={
    val arr = new ArrayBuffer[String]
    // val arr1 = ArrayBuffer[String]()
    //增加元素, 不生成新的对象
    arr += "ni"
    arr  += "ni"

    arr.insert(0,"wo","shi")
    println(arr)

    arr.remove(0)//删除下标是0的元素
    println(arr)
  }

  //增加新的元素，生成新的
  def test1: Unit ={
   val arr   =  ArrayBuffer[Int](1,3,5,8,9)
    //产生一个新的对象
    val arr1 = arr :+ 10
    val arr2 = arr .+= (100,200)
    println(arr1)
    println(arr2)

  }


  //3. 两个对象相减
  def test2: Unit ={
    val buffer1 = ArrayBuffer[Int](10, 10, 20, 30)
    val buffer2  = ArrayBuffer[Int](100, 200, 300, 400, 10, 10)


    val b3 = buffer1 ++ buffer2
    val b4 = buffer1 ++: buffer2
    //两个集合相减
   val b5 =  buffer1 --= buffer2
    println(b3)
    println(b4)
    println(b5)

  }


  //取集合的最大最小等值
  def test3: Unit ={
    val arr = ArrayBuffer[Int](100, 200, 300, 400, 10, 10,50,77)
    //测试
    val max = arr.max
    val min = arr.min
    val head = arr.head
    val size = arr.size
    val sum = arr.sum
    val length = arr.length
    //最后一个
    val last = arr.last
    println(last)
    //取除第一个以外的其他值
    val tail= arr.tail
    println(tail)
    //take: 取前3 个
    val take = arr.take(3)
    println(take)
  }

  //创建集合的2种方式
  def test5: Unit ={
    var arr: Array[Int] = Array[Int](1,10,20)
    println(arr.mkString("\t"))

    //定义一个可变的arrya; 默认2个 ， 可以添加多个
    val arr21: ArrayBuffer[Int] = new ArrayBuffer[Int](2)

    arr21 += 2
    arr21 += 3
    arr21 += 1

    arr21.foreach(data=>println(data))


  }
}
