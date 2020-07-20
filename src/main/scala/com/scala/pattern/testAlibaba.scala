package com.scala.pattern

/**
  * @author kylinWang
  * @data 2018/1/13 20:32
  *
  */
object testAlibaba {
  def main(args:Array[String]): Unit ={
      for(i<-1 to 10 ; j <-1 to 8){
        println(i*j)
      }



  }
}


abstract  class person(val name:String, val age:Int){

  val a :Int
  def getadd():Long

}

/*

class student(override val name:String, Override val age:Int)  extends person(name,age){


}*/
