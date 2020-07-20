package com.elianda.test1

import java.sql.Time

object TimeTest {
  def main(args: Array[String]): Unit = {
    val time = new Time(System.currentTimeMillis())

    val time2 = System.currentTimeMillis()

    println(time)

    println(time2)

  }

}
