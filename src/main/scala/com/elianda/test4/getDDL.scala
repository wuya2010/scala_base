package com.elianda.test4

import java.io.File

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * @author kylinWang
  * @data 2018/12/25 15:19
  *
  */
object getDDL {
  def main(args: Array[String]): Unit = {

      test3
  }



  def test1={
    val source = Source.fromFile("E:\\ChinaBank\\first_project - test\\config\\DDL\\ddl_relm.conf").getLines()
    var map = Map[String,String]()
    //将数据放入map中
    source.foreach(str => {
      map += str.split("=")(0) -> str.split("=")(1)
    })

    map.foreach(println(_))
  }


  def test2={
    val source = Source.fromFile("E:\\ChinaBank\\first_project - test\\config\\DDL\\MCIS_SMSPSIGN_F").getLines()
    //val source =Array("rec_no                                            string                                            记录号 ")
    val ddl_schema  = new ArrayBuffer[String]
    source.foreach(str=> ddl_schema += str.split("\\s")(0).toUpperCase())
    ddl_schema.foreach(println(_))

  }

  def test3 ={
    val ddl_json = Source.fromFile("E:\\ChinaBank\\first_project - test\\src\\main\\resources\\base4.json").getLines()

    ddl_json.foreach(str=>
      str.split(",").map(dt=>(dt.split(":")(0)))//,dt.split(":")(1
    )
    ddl_json.foreach(println(_))

  }


}
