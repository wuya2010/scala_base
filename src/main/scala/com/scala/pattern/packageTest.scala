package com.scala.pattern
import java.util
/**
  * @author kylinWang
  * @data 2018/1/13 20:56
  *
  */
object packageTest {

  def main(args: Array[String]): Unit = {
    //代码要多写，多看
    new util.HashSet[String]()
    new util.ArrayList[String]()
    Dog("read").spark()
    
  }

}

object Dog{
  
  def apply(color:String)= new Dog(color)
}

//传入参数并输出
class Dog(color: String){
  def spark(): Unit ={
    println("spark"+color)
  }
}

