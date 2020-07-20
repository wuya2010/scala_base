package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 15:08
  */
object PatternType2 {
    def main(args: Array[String]): Unit = {

        //Array[Int]   1,2
        // foo(Array(1, 2))

        //数组匹配
        foo(Array(1, 2.2))

        //map的匹配
         foo(Map(1 -> 97.8))


        //新建一个空的集合
        //foo(1.1 :: Nil)
        
    }
    
    def foo(obj: Any) = {
        
        obj match {
            // 匹配数组类型  new int[]  这里泛型起作用，是因为array的底层是java数组，所以起作用
            case a: Array[Int] => println("Array[Int]   " + a.mkString(","))


                //map的泛型在这里不起作用： Map(1 -> 97.8)可以和下面的式子匹配
            //case a: Map[String, Int] => println("Map[String, Int]   ")

                //map这里可以和任意的匹配，所以可以2个——
            case a: Map[_, _] => println("Map[_, _]   ")

                //除了array的泛型有用，其他集合都没有用的
            case a: List[_] => println("List[_]..")


            case _: Int =>

            case _ =>
        }
        
    }
}


/*
泛型擦除

 */