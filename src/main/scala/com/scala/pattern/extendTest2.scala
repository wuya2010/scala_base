package com.scala.pattern

/**
  * @author kylinWang
  * @data 2018/1/13 20:43
  *
  */
object extendTest2 {

  def main(args: Array[String]): Unit = {
    val stu:stu = new stu  //注明类的来源
    stu.say
  }

}

class Person{
  def say={
    println("xx")
  }
}

class stu extends Person{
  override def say: Unit ={
    print("yy")
  }
}