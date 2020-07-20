package com.base.fun

/**
  * Author kylin
  * Date 2018-09-04 10:57
  */
object LazyDemo {
    
    /*lazy val a = {
        println("a...")
        10
    }*/
    
    def main(args: Array[String]): Unit = {
        println(a)
        println(a)
    }
    
    val a = { 10 }
    lazy val b = { 20 }
    def c = 30
}

/*
惰性求值

val a = { 10 }
lazy val b = { 20 }
def c = 30
 */