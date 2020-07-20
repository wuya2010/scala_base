package com.base.sort

object SortDemo1 {
    def main(args: Array[String]): Unit = {
        /*val list1 = ArrayBuffer(30, 50, 70, 60, 10, 20)
//        val list2 = list1.sorted.reverse

        //默认就是自然排序 ： 升序
        list1.sorted
        
        println(list1)
        println(list2)*/


        //继承  Ordered  就可以直接进行比较 让元素本身具有比较能力  输出： true / false
        println(new User(20, "lisi") > new User(10, "zs"))

        val users = List(new User(20, "lisi"), new User(10, "zs"), new User(15, "wangwu"), new User(15, "abc"))
        
        println(users.sorted)
    }
}


// 方法2：实现comparator接口， 重写comparare方法
// 利用Ordered这个方法， 就可以用 > 或 < 进行比较

//ordered[A] extends Any with java.lang.Comparable[
class User(val age: Int, val name: String) extends Ordered[User] {

    //自定义类需要重写toString方法
    override def toString: String = s"[$name, $age]"
    
    override def compare(o: User): Int = {

        var r = o.age - this.age   //写的方向
        if (r == 0) {
            r = this.name.compareTo(o.name)
        }
        r
    }
}


/* 方法1：但对于自定义的类，继承Comparalb类（这里有compareTo 方法）  ==》比较java的方式

class User(val age: Int, val name: String) extends Comparable[User] {
    override def compareTo(o: User): Int = {
        var r = o.age - this.age
        if (r == 0) {
            r = this.name.compareTo(o.name)
        }
        r
    }
    
    override def toString: String = s"[$name, $age]"
}
*/


/*
排序的本质其实就是比较元素的大小
sorted：(implicit ord:Ordering)  ==>Int可以模式自然排序

 方法1：但对于自定义的类，继承Comparale类（这里有compareTo 方法）

 方法2：实现comparator接口， 重写comparare方法


排序算法:
    冒泡
    选择
    插入
    
    归并
    希尔
    快速
    

    

3个支持排序的算子:

sorted

sortBy

sortWith


 */