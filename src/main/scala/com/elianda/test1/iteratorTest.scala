package com.elianda.test1

object iteratorTest {
  def main(args: Array[String]): Unit = {
  val s1 = Iterator("appg_mode  string  增量方式","appg_date  string  增量日期","c")
    val result = s1.map(f => {
      f.split("\\t").filter(p => !p.trim.equals(""))
    })
    //schema=col1,col2,col3,col4
    //获取列名
    println(result)
    val schema = result.map(f => f(0).toLowerCase().trim).mkString(",")

    println(schema)
  }
}
