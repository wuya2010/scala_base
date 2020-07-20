package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 16:19
  */


//匹配序列
object PatternObj2 {
    def main(args: Array[String]): Unit = {
        val names = "lisi,za,ww,zhiling"
        
        names match {
            //匹配一堆  :用a来接
            case Names(one, two, a@_*) => println(one)


            // case Names(one, two, three, four) => println(one)
        }
    }
}


//对象提取器:
object Names{
    // 匹配序列
    def unapplySeq(s: String) = {
    if(s.length == 0) None
        //用some
    else Some(s.split(","))
}
}
/*


 */

