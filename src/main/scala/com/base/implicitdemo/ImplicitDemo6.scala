package com.base.implicitdemo

/**
  * Author kylin
  * Date 2018-09-09 11:02
  */
object ImplicitDemo6 {
    
    def main(args: Array[String]): Unit = {
        
        foo
    }

    //A是一个泛型类B
    def foo(implicit a: A[B]) = {
    
    }
}

object A {

    //伴生对象中也能找到，下面这种情况也能找到
    //implicit val a: A[B] = new A[B]

}
class A[B] {
    //伴生类中不可以
    //非静态属性，要有new A才可以用
}

object B{

    //放在泛型的伴生对象中也是可以的
    implicit val a: A[B] = new A[B]
}
class B


/*
隐式转换:
    隐式转换函数
       implicit def double2Int(d: Double): Int = d.toInt
    
    隐式类
        其实是隐式转换函数的语法糖
    
    隐式参数和隐式值
        他们配合使用
        
隐式转换函数, 隐式类, 隐式值的查找:
    1. 在当前使用的作用域内查找
    2. 去相关类型的伴生对象中查找
    
 */