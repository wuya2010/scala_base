package com.scala.PartitionFun

/**
  * @author kylinWang
  * @data 2018/1/13 19:13
  *
  */
object sortTest {
  def main(args: Array[String]): Unit = {

test1
  }

  def test1={
    val users = List(new User1(20, "lisi"), new User1(10, "zs"), new User1(15, "wangwu"), new User1(15, "abc"))

    users.sortBy(str => (str.age,str.name))(Ordering.Tuple2(Ordering.Int.reverse,Ordering.String.reverse))

  }

}
case class  User1(val age: Int, val name: String)