package com.scala.collect

/**
  * @author kylinWang
  * @data 2018/12/22 23:33
  *
  */
object IeratorTest {

  def main(args: Array[String]): Unit = {
    test3
  }

  def test0: Unit ={
    val it = Array("1","2","5","1").toIterator.toList
    //List(1, 2, 5, 1)
    println(it)

    val it1 = Array("1","2","5","1").toIterator
/*    for (elem <- it1) {
      println(elem)
    }*/

    println("==============测试Iterator================")
    //这里要注意 iterator 具有一次性，要持久使用需要转为 list
    while(it1.hasNext){
      println(it1.next())
    }

    println("==============测试set================")
    val it2 = Array("1","2","5","1").toIterator.toSet

    // it.foreach(println(_) : 这里不能传空
    it2.foreach(println(_))
  }


  //测试reduce
  def test1: Unit ={
    //
    val test1 = List("a", "b", "c")
    //不能识别的 _ ,具体注明出来
    val test2 = test1.reduce((x,y)=>(x +"_"+ y))
    println(test2)

    //使用scanRight
    val list1 = List(30, 50, 70, 60, 10, 20)
    val list2 = list1.scanRight(100)(_ + _)

    //List(340, 310, 260, 190, 130, 120, 100): 原来的和240
    println(list2)

  }


  //测试zip
 def test3{
   val list1 = List(30, 50, 70, 60, 10, 20, 100, 200)
   val list2 = List(3, 5, 7, 6, 1, 2)


   // 以少的数为准
   //List((30,3), (50,5), (70,7), (60,6), (10,1), (20,2)) ； 生成一个新的list
  val list3 =  list1.zip(list2).toMap
   val list_ors = list3.map(_._1)
   println(list_ors)

  println("===============")

   // 给每一个元素一个下角标
   //List((30,0), (50,1), (70,2), (60,3), (10,4), (20,5), (100,6), (200,7))
  val list4 =  list1.zipWithIndex
   println(list4)

   println("========last one=======")

   val t = List((10, 1), (20, 2), (30, 3))
   println(t.unzip)

}









}
