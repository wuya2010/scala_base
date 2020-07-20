package com.scala.OptionTest

/**
  * @author kylinWang
  * @data 2018/7/14 10:58
  *
  */
object OptionTest{

  def main (args: Array[String] ): Unit = {

    val list1 = Array("1","2","3")
    val  list2 =  Array("a","b","c")
    val test = Seq(list1,list2)
//     val t = test.foldLeft("")((x, y) => {
//      val mid = for(i<-x) yield y.map(j => j + x )
//      mid.flatten
//    })


   Option(list1) match{
     case Some(a) => a.foreach(println)
     case _ => println("为空")
   }



  }

}
