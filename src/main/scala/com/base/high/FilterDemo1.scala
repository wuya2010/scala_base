package com.base.high

/**
  * Author kylin
  * Date 2018-09-07 15:09
  */
object FilterDemo1 {
    def main(args: Array[String]): Unit = {
        //        val list1 = List(30, 50, 7, 60, 1, 20)
        //        val list2 = list1.filter(x => x % 2 == 1)
        //        val list2 = list1.filter(_ % 2 == 1)
        //        println(list2)
        
        // 所有的偶数的平方
        //       val list2 = list1.filter(_ % 2 == 0).map(x => x * x)
        //        val list2 = list1.filter(_ % 2 == 0).map(_ + 1)
        //        println(list2)
        
        val list1 = List(null, 30, 50, 70, 60, 10, 20, true, "a")
        val list2 = list1
          //filter不能改变元素类型
          .filter(_.isInstanceOf[Int])
          //改变每一个元素的类型
          .map(_.asInstanceOf[Int])
            .map(_ + 1)
        println(list2)
        
    }
}

/*

 */