package com.elianda.test1

object getConfig {

  def main(args: Array[String]): Unit = {

    //放入map中
   val str = Map("schema"->"APGMOD STRING,APGDAT STRING,SNDBNK STRING,MSGSEQ STRING,MSGTIM STRING,MSGTYP STRING")

    val add_col = Array("a","b")

    //如何获取左边一行
    val getName = str.getOrElse("schema",null).split(",").map(str=>str.split(" ")(0))//.flatMap(_.split(" "))

    //增加列
    val getName2 = getName ++ add_col

    getName2.foreach(println)





  }

}
