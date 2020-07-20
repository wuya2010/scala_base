package com.base.tuple

/**
  * Author kylin
  * Date 2018-09-07 10:40
  */
object TupleDemo1 {
    def main(args: Array[String]): Unit = {
        /*val t2 = Tuple2(10, "abc")
        println(t2._1)
        println(t2._2)*/
        
        val t2 = (1, "abc", true)
        println(t2)    //(1,abc,true)
        println(t2._1)   //1
        // 遍历元组, t2.productIterator 生成迭代器
        for (elem <- t2.productIterator) {
            println(elem)
        }


/* ==>遍历元素       1
                  abc
                true*/
    
        println(/%(10, 3))   // RDD[(key, value)]   (3,1)
    }
    
    def /%(a:Int, b: Int) = (a / b, a % b)
    
    
}
/*

Tuple 元组


 */