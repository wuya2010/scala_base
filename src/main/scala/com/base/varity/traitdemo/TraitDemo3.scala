package com.base.varity.traitdemo

/**
  * Author kylin
  * Date 2018-09-06 15:55
  */
object TraitDemo3 {
    def main(args: Array[String]): Unit = {
        
    }
}

class A1{
    def foo() = {
    
    }
}


//trait 可以继承类
trait T1 extends A1{
    override def foo() = {
    
    }
}


class B1 extends A1 with T1




/*

1. trait 可以继承类

 */