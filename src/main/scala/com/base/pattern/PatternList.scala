package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 15:45
  */
object PatternList {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3,4)
        list match {

            //
            //            case List(_, a, _, _, _) => println(a)



            //匹配第一个： （1，List(2,3,4)）  用变量b存放其他元素
            //            case List(a, b@_*) => println(a, b)


            //输出： (1, 2, List(3,4))  中值表达式   list专用
            case a :: b :: c=> println(a, b, c)

            //只取前3个元素（1，2，3）
            case a :: b :: c :: Nil => println(a, b, c)
        }
        
    }
}

/*
中置表达式:
    1 + 2
   

 */
