package com.base.varity.obj

/**
  * Author kylin
  * Date 2018-09-06 09:11
  */
object ObjDemo1 {
    def main(args: Array[String]): Unit = {
        val a = new User("a", 20, "m")

        //调用构造函数： 根据形参待用构造函数
        new User()
        new User("")

        //new User("a", 20)
        
    }
}

//这里sex可以给定一个默认值 ： 主构造函数
//主构造的形参，构成类的属性
class User(var name: String, val age: Int, sex: String) {
   //类里面的属性
    var a: Int = 0
    val b: String = ""
    
    println("aaa1")



    //构造函数，这里是没有返回值的，没有“=”
    //首行必须调用主构造函数
    def this() {
        
        this("lisi", 20, "f")
        //...
    }


    //辅构造的形参，仅仅是一个局部常量，不能当变量
    //不能加val var
    def this(name: String) {
        this(name, 20, "f")

    }


    //a出来这个函数都用不了
    //辅构造调用其他辅构造的时候, 只能后面调用前面的
    def this(a: Int) {
        this()
        // a只能在这里使用
    }
    

    //主构造函数里面定义的函数
    def foo = {
    
    }
}



/*

1. scala也支持构造函数的重载.

2. 其他的构造函数应该使用  def this() = {}

3. 构造函数分两种:
    主构造函数
        1. 只能有一个
        
        2. 他的形参会自动的成为类的属性
    
    辅构造函数
        1. 可以有多个
        2. 首行必须是调用主构造
        3. 他的形参仅仅是一个普通的局部常量
        4. 辅构造调用其他辅构造的时候, 只能后面调用前面的

 */