package com.base.loop

/**
  * Author kylin
  * Date 2018-09-04 09:01
  */
object ForDemo1 {
    def main(args: Array[String]): Unit = {
        /*for (i <- 1 to 10; j = i * i) {
            println(i, j)
            
            
        }*/
        
        for (i <- 1 to 9) {
            for (j <- 1 to i) {
                print(s"$j * $i = ${i * j}\t")
                if (i == j) println()
            }
        }
        println()
        //
        for (i <- 1 to 9; j <- 1 to i) {
            print(s"$j * $i = ${i * j}\t")
            if (i == j) println()
            
        }
    }
}
