package com.base.high

object ReduceDemo1 {
    def main(args: Array[String]): Unit = {
        //        val list1 = List(30, 50, 70, 60, 10, 20)
        //        val result = list1.reduce((x, y) => x + y)
        //        val result = list1.reduce(_ + _)
        //        println(result)
        
        val ss = List("a", "b", "c")
        val result = ss.reduce((x, y) => x + "-" + y)

        //简约形式1
          //        println(ss.reduce((x, y) => x + "-" + y))
        //简约形式2
       //println(ss.reduce(_ + "-" + _))

        println(result)
    }
}

/*
reduce:
 

 */
