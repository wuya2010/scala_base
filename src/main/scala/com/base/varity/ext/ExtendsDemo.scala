package com.base.varity.ext

/**
  * Author kylin
  * Date 2018-09-06 10:25
  */


/*
scala： 单继承
 */
object ExtendsDemo {
    def main(args: Array[String]): Unit = {
        //new stu: 运行时类型
        //stu：编译时类型  左边最好写抽象类或接口
        val stu: Person = new  Stu
        stu.say()

        //Person中并没有eat()方法
//     stu.eat()
    }
}
class Person{
    def say() ={
        println("person say...")
    }
}

class Stu extends Person{
    override def say(): Unit = {
        println("stu say...")
    }
    
    def eat() = {
    
    }
    
}


/*
面向对象三大特征:
    1. 封装
    
    2. 继承
        单继承
        
    3. 多态
        编译时类型
        
        运行时类型
        
        编译时类型和运行时类型不一致就是多态
        1. 编译能否通过看编译时类型
        2. 执行的具体表现, 看运行时类型
        
        
继承:
 1. 方法的覆写, 必须添加override关键
 
 2. 属性的覆写:
 
 
 

 */