package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 16:09
  */
object PatternTuple {
    def main(args: Array[String]): Unit = {
        val t = (1, 2, 3)


        //元组匹配
        t match {

            //拿出第一个
            case (a, b, c) => println(a)

                //第一个必须是1 ，第二个随便 ， 拿出第3个
            case (1, _, c) => println(c)
        }
    }
}
/*

可以匹配的包括：
数组
list
元组

不包括： map与set
 */