package com.base.pattern

/**
  * Author kylin
  * Date 2018-09-09 15:31
  */
object PatternArray {
    def main(args: Array[String]): Unit = {
        val arr: Any = Array(10, 20, 30, 20, 20)
        
        arr match {
            // 匹配长度3个，正好是10，20，30，变量arr中元素： 多一个少一个都不行
            //            case Array(10,20,30) => println("Array(10,20,30)...")


            // 长度写死了必须是3个 ，且第一个是10
            //            case Array(10, _, _) => println("Array(10,_,_)...")


            //  长度3个，获取第二个值，用变量a拿值，a的v变量类型要与arr相同
            //            case Array(10, a, _) => println(s"Array(10,$a,_)...")


            //  长度不限制    _*, 只匹配第一个 ， 这里没有变量来接
            //            case Array(10, _*) => println("Array(10, _*)")


            //取出来用变量a来接，匹配之后的数组存到a中
            case Array(10, a@_*) => println("Array(10, _*)" + a.mkString(", "))

            case _ =>
        }
    }
}

/*
匹配对象:
    匹配数组的内容:


 */