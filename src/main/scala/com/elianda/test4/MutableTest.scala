package com.elianda.test4

import org.apache.commons.lang.mutable.Mutable

import scala.collection.mutable
import scala.collection.parallel.immutable




/**
  * @author kylinWang
  * @data 2018/12/25 9:51
  *
  */
object MutableTest {

  def main(args: Array[String]): Unit = {
    test2
  }

  //val的map, 无法直接添加
  def test={
    val map = Map[Int,Int](1->2,3->4)
  //  map += 5->6
    println(map)  //Map(1 -> 2, 3 -> 4, 5 -> 6)
  }



  //var的map； 添加的元素可以直接给出 ; var引用的地址变化
  def test1={
    var map = Map[Int,Int](1->2,3->4)
    println(map.hashCode())
    map += 5->6  // -982998425
    println(map)  //Map(1 -> 2, 3 -> 4, 5 -> 6)
    println(map) // -1141186891
  }

  def test2={
    val map = mutable.Map[Int,Int](1->2,3->4)
    println(map.hashCode()) //-982998425
    //map += 5->6
    println(map.hashCode())//-114118689
    println(map)  //Map(5 -> 6, 1 -> 2, 3 -> 4)
  }

  def test3={
    var map = mutable.Map[Int,Int](1->2,3->4)
    println(map.hashCode())//-982998425
    map += 5->6
    println(map.hashCode())//-1141186891
    println(map)  //Map(5 -> 6, 1 -> 2, 3 -> 4)
  }


}
