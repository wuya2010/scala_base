package com.base.varity.traitdemo.sub

/**
  * Author kylin
  * Date 2018-09-06 16:31
  */
object TypeDemo {
    def main(args: Array[String]): Unit = {
        // 给类起别名
        type P = Personhenhenchangdeyigelei
        
        val p = new P()
    
        println(p.isInstanceOf[P])
        println(p.isInstanceOf[Personhenhenchangdeyigelei])
        println(p.getClass.getSimpleName)
        
    }
}

class Personhenhenchangdeyigelei
/*

 */