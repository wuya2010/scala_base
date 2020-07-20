package com.base.other

/**
  * Author kylin
  * Date 2018-09-10 14:05
  *
  *
  *
  */
object TypeDemo1 {
    def main(args: Array[String]): Unit = {
        val some: Option[Int] = foo()

        //==》最原始的获取方法： some.get就可以得到some（10）的值
    //    println(some.get)


        //避免空值，可以采用下面这种方法
        //用这种方式比较好
      //  println(some.getOrElse(100))

        //方法三： 模式匹配
        some match{
            case Some(a) => println(a)
            case None =>
        }


        //二者选其一：与some相似
        /*val e: Either[String, Double] = foo1(-100)
        if (e.isRight) {  //正确的结果
            println(e.right.get)
        }else{
            println(e.left.get)
        }*/


        foo1(-100) match {
            case Left(v) => println(v)
            case Right(v) => println(v)
        }
        
        foo_:()
    }



    // ::  ::: +:  :+  /:  :\
    def foo_:() = {
    
    }


//有2个类： Right一般表示正确  Left： 错误的结果
    def foo1(a: Double): Either[String, Double] = {
        if(a < 0){
            Left("你传的参数不合法")
        }else{
            Right(math.sqrt(a))
        }
    }


//some：表示这个东西是存在的
    def  foo():Option[Int] = {
        Some(10)
//        None
    }
    
}
/*
特殊类型：

Any
AnyVal
AnyRef
Nothing

Option
    空指针问题
    Some
    None
    
Either
    Left
    Right


====================================================
总结：


类型推断:
    1. 推得类型和预期不一致
        val a: Double = 1
    2. 递归函数的返回值不能推导
    
    3. 函数属性如果使用 _ 作为默认值, 不能推导
        class A{
            val a :Int= _
        }
    4. 当显示的使用return, 返回值类型也不能推导
    
_ 使用总结:
    1. 通配符到如包或者类
    2. 匿名函数的省略 _
    3. 部分应用函数
        val sqrt = math.pow(_, 2)
    4. 访问元组的元素
        t._1, ....
        
    5. 模式匹配中的统配匹配
    
    6. 利用 _ 去给属性设置默认值 (数字: 0, 对象: null, boolean false)
    
    7. 传递函数, 而不是调用函数
        def foo(){  }
        
        val f = foo _
    8. 异常catch
    
    9. 分解操作:  rest: _*  rest@_*
    
    10. 用于定义标识符中包含 :   foo_:
    
 
 方法的约定:
    运算符结合性
    
    :
    
 apply和update:
  
  apply:
    1. 在伴生对象中
       伴生对象(...) 其实就是在调用伴生对象的apply方法
    2. 在伴生类中
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
 
 
 
 
 

 */