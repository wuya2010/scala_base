package com.base.sort

object SortDemo3 {
    def main(args: Array[String]): Unit = {
        val users = List(new User1(20, "lisi"), new User1(10, "zs"), new User1(15, "wangwu"), new User1(15, "abc"))

       //sortBy[B](f: A => B)(implicit ord: Ordering[B]) ： 函数结构

   //默认是升序，降序需要改     这里有2个元素，因此是一个元组，Ordering.Tuple2
        val r = users.sortBy(user => (user.age, user.name))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.String.reverse))
        println(r)




        //        val list1 = List(30, 50, 70, 60, 10, 20)

        //默认升序 ：Ordering.Int.reverse
        //        println(list1.sortBy(x => x)(Ordering.Int.reverse))


        //        val ss = List("abc", "hello", "atugigu", "aaaaaaa")

                //长度Int 用Ordering.Int.reverse
        //        println(ss.sortBy(x => x.length)(Ordering.Int.reverse))
        
    }
}


/*
排序的本质其实就是比较元素的大小
1. 让元素本身具有和其他元素比较的能力  (Comparable   compareTo(other) this other   => Ordered compare   this other)
2. 第三方的比较机构来进行比较大小  (Comparator compare(O1, O2))


排序算法:
    冒泡
    选择
    插入
    
    归并
    希尔
    快速
    
    

    

3个支持排序的算子:

sorted
    1. 让对象具有比较的能力(Ordered)
    2. 提供第三方的比较器(Ordering)

sortBy

sortWith
 


 */