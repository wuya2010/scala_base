//package com.base.varity.ext
//
//import com.atguigu.{A, B}
//
///**
//  * Author kylin
//  * Date 2018-09-06 10:25
//  */
//
///*
//scala中属性也可以覆写
// */
//object ExtendsDemo1 {
//    def main(args: Array[String]): Unit = {
//        val b = new B
//        val a: A = b
//
//        System.out.println(b.n) // 100
//
//        System.out.println(a.n) //
//
//
//    }
//}
//
//class A {
//    //没有参数的def
//    def n: Int = 10
//
////    var a: Int = 1
//}
//
//class B extends A {
//    override val n: Int = 100
//
////   override var a: Int = 100
//}
//
//
///*
//
//继承:
// 1. 方法的覆写, 必须添加override关键
//
// 2. 属性的覆写:  ==》scala中属性也能覆写
//        1. val 只能覆写val和没有参数的def
//
//        2. var 只能覆写抽象的var
//
//
//
// */