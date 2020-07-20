package com.scala.DF_DS_Test

import java.text.SimpleDateFormat
import java.util.Calendar
;

/**
  * @author kylinWang
  * @data 2018/6/15 10:28
  *
  */
object ZGTest {
  def main(args: Array[String]): Unit = {


    val format = new SimpleDateFormat("yyyy-MM-dd")
    val cal = Calendar.getInstance()
    val sss = cal.getTime()
    println(sss.getTime)


//    val tt = abc("nihao")

//    val abc = udf((a1: String, a2: String) => {
//
//      if (a1 == null || a1.isEmpty) {
//        sss.getTime
//      }
//      else {
//        //unix_timestamp(to_date(lit("20180101"),"yyyymmdd"))
//        sss.getTime
//      }
//    })
//
//    println(abc)

  }
}
