package com.base.implicitdemo

/**
  * Author kylin
  * Date 2018-09-09 11:02
  */
object ImplicitDemo7 {

    //可以放入到main方法中，也可以在外面   好处： 可以在多个地方调用，但是只能由一个
    implicit val aaaa = 100
    
    
    def main(args: Array[String]): Unit = {
        //隐式值和默认值都可以不传
        foo1(100)    //这里可以直接传

        //foo1   没有括号，这样不行，需要声明一个隐式值

        foo(200)(1)

        // 在Ordering / Char（相关类型的对象中）中查找
        //在当前使用的作用域中查找
        //在相关类型的伴生对象中查找
        Array(1, 2).sortBy(x => x)
        
    }

    //这里可以对隐式值，定义一个默认值implicit a:Int=200
    //在foo时，调用的时隐式值，在foo（）,调用的时默认值

    def foo(n: Int)(implicit a:Int) = {
        println(n + a)
    }

    //隐式参数
    def foo1(implicit a: Int) = {
        println(a)
    }
}


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