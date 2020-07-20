package com.elianda.test1

object getType {

  def main(args: Array[String]): Unit = {

    val schema = "INCFLG STRING,CURWKDY STRING"

    val strings = schema.split(",").map(_.split(" ")).toList

    val strings2 = schema.split(",").map(_.split(" ")).map(data=>data(0)->data(1)).toMap.map(_._1)

    println(strings2)

  //  strings2.foreach(_._1)

   //strings.foreach(data=>println(data.mkString("\t")))

  }
}
