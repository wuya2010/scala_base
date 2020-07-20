package com.base.map

/**
  * Author kylin
  * Date 2018-09-07 11:27
  */
object MapDemo1 {
    def main(args: Array[String]): Unit = {
//        val map = Map("a" -> 97, "b" -> 98, "d" -> 100, "c" -> 99, "d" -> 99)
        
        //        println(map("a"))
        //        println(map("b"))
        //        println(map("f"))
        //        val v = map.getOrElse("a", 102)
        //        println(v)
        
        /*for (kv <- map) {
            println(kv._1, kv._2)   //输出map所有得key-value
        }*/
        
        /*for ((_, v) <- map) {  // 模式匹配   输出所有value得值
            println(v)
        }*/
        
        /*for ((k, v) <- map if v < 99) {
            println(k)
        }*/
        
        
        val map = Map((1, 10), (2, 20), 2 -> 30)
        println(map)                //Map(1 -> 10, 2 -> 30)
    }
}

/*


 */