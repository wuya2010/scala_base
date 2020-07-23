//package com.base.varity.ext
//
//import com.atguigu.{A, B}
//
///**
//  * Author kylin
//  * Date 2018-09-06 10:25
//  */
//object ExtendsDemo2 {
//    def main(args: Array[String]): Unit = {
//        val b = new B
//
//
//
//    }
//}
//
//class AA(val n: Int) {
//    /*def this(){
//        this(10)
//    }*/
//}
//
////会调用父的构造器，需要传一个参数
////class BB extends AA（需要加参数） {
//class BB() extends AA(1) {
//
//    override val n: Int = 100
//
//
//}
//
//
///*
//
//
// 3. 继承的时候构造器的调用:
//    1. 子类的主构造才有权利去调用父类的构造(主或者辅)
//
// */