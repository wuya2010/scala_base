package com.base.varity.single

/**
  * Author kylin
  * Date 2018-09-06 15:22
  */
object TypeDemo {
    def main(args: Array[String]): Unit = {
        var b: C = new B
        println(b.isInstanceOf[C])
        b.asInstanceOf[B].foo()
        
    }
}


class C
class B extends C{
    def foo() = {
        println("foo...")
    }
}