package com.base.set

/**
  * Author kylin
  * Date 2018-09-07 13:58
  */
object SetDemo1 {
    def main(args: Array[String]): Unit = {
        /*var set1 = Set(10,20,10,30,30, 10)
        set1 += 100     //Set(10, 20, 30, 100)  去重 Set默认是不可变集合，数据无序
        set1 -= 10      //Set(20, 30)
        set1 ++= List(10, 1,2)    //Set(10, 20, 1, 2, 30)   增加元素
        println(set1)*/
        //        var set1 = mutable.Set(20, 30, 10, 1)
        //        println(set1)


        //to duplicate： 复制
        //        println(toDuplicate(List(1, 1, 2, 3, 4)))    //List(1, 2, 3, 4)
        
        val set1 = Set(30, 50, 70, 60, 10, 20)
        val set2 = Set(30, 50, 7, 6, 10, 2)
        
        // 并集
        println(set1 ++ set2)
        println(set1.union(set2))
        println(set1 | set2)
        // 交集
        println(set1 & set2)
        println(set1.intersect(set2))
        // 差集
        println(set1 &~ set2)
        println(set1 -- set2)
        println(set1.diff(set2))
        
    }
    
    def toDuplicate(list: List[Int]) = {
        /*(Set[Int]() ++ list).toList*/    //toList转化为 Seq集合：list  创建一个List（数据有顺序，可重复）
        list.toSet.toList
    }
}

/*
无序不可重复
 */