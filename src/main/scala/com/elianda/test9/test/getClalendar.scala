package com.elianda.test9.test

import java.util.Calendar

/**
  * @author kylinWang
  * @data 2018/6/2 17:34
  *
  */
object getClalendar {
  def main(args: Array[String]): Unit = {

      val calendar = Calendar.getInstance()
      calendar.set(Calendar.DAY_OF_MONTH,0)
      val day = calendar.get(Calendar.DAY_OF_MONTH)
      println(day)

  }

}
