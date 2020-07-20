package com.scala.pattern

/**
  * @author kylinWang
  * @data 2018/1/13 20:48
  *
  */
object extendTest3 {
  def main(args: Array[String]): Unit = {

    val iphone = new iphone

  }

}

class apple(val n : Int){

}

class iphone() extends apple(1){
  override val n:Int = 2
}