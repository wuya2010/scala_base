package com.scala.grammer

/**
  * @author kylinWang
  * @data 2018/12/19 23:14
  *
  */
object IfDemo {

  //从1 + 100 求和
  def main(args: Array[String]): Unit = {

    println(getadd(12))

    getCompare(13)

    println("===============================")
    // 获取array的值
    println(getArray)

    println(testWhile)

    testDemo

  }


  //1. 定义一个方法，计算给定值到1 的和
  def getadd(num:Int)={
      var sum = 0
      for(i<-1 to num){
        sum += i
      }
     sum
  }

//2. 比较2个值, 传一个值，给一个初始值
  def getCompare(b:Int ,a: Int = 10)={
        if(b>a) println(s"我是b,我的值是${b}") else println(s"我是${a}")
  }


  //3. 定义一个array的函数
  def getArray ={
    val arr_1 = List[Array[String]](Array("1","J"),Array("2","M"))
    val arr_2 =  Array[String]("1","3")
        try {
        //  val t = arr_1.foreach(str => str)
          var map = Map[String, String]()
          arr_1.foreach(str => {

              map += str(0) -> str(1)

            // pritln(data.mkString("\t"))
          })
          map   //作为返回值，并没有显示处理
        } catch {
          case e => e.printStackTrace
        }
  }


  //写一个while方法
  def testWhile ={
    //强类型，可自动推断类型
    var i = 0
    while(i <= 100){
        i += 1
    }
    i
  }


  def testDemo={
    val i: Int = 2 + 3
    val getNew = 2.toString

    println(2 toString)
  }



}
