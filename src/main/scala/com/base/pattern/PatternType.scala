package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 15:08
  */
object PatternType {
    def main(args: Array[String]): Unit = {
        val s = List(1, 2, "a", "abc", "+", "-", true)
        
        /*for(c <- s){
            c match {
                case a: Int => println(a + 1)
                case "+" => println(1)
                case "-" => println(1)
                case a => println(a)
            }
        }*/
        
        for (c <- s) {
            c match {
                // 匹配类型  用变量a接受满足条件的
                case a: Int => println(a + 1)
                // 添加守卫
                case op: String if op == "+" || op == "-" => println(1)
                    //用变量a来接受
                case a => println(a)
            }
        }
    }
}
