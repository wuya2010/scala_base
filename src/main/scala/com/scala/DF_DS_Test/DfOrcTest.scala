package com.scala.DF_DS_Test

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2018/6/10 9:46
  *
  */
object DfOrcTest {

  def main(args: Array[String]): Unit = {

      val conf = new SparkConf().setAppName("Test").setMaster("local[5]")
      val sc = new SparkContext(conf)
      //DF 与 DS
      val spark = SparkSession.builder().config(conf).getOrCreate()
      sc.setLogLevel("WARN")

      val ods_orc = sc.parallelize( Array(("20180101","SZ",101,"SFZ","420984"),
          ("20180201","SZ",102,"SFZ","420984"),
          ("20180301","SZ",103,"SFZ","420984")))
      import spark.implicits._
      val ds_ods_orc = ods_orc.toDF()
     //保存ods_orc 文件
//      ds_ods_orc.write.format("orc").save("./ods_orc")

      //读取文件
        spark.read.format("orc").load("./ods_orc").show()//.toDF("id","name")

//      +---+----+
//      | id|name|
//      +---+----+
//      |  1|  sz|
//      |  2|  bj|
//      +---+----+


      println("================ORC,带schema ==================")
      val orc = sc.parallelize(Array(MyDfx("20180401","BJ",104,"SFZ","420984"),
          MyDfx("20180501","BJ",105,"SFZ","420984"),
          MyDfx("20180601","BJ",106,"SFZ","420984")))

      val ds_orc = orc.toDF()

      ds_orc.write.format("orc").save("./orc")

      //读取文件
      spark.read.format("orc").load("./orc").show()



  }
   case class MyDfx(trn_date:String,//交易日期
                    sys_id:String,//系统代码
                    cst_id:Int,//客户号
                    ctf_tp:String,//发薪单位证件类型
                    ctf_no:String//证件号
//                    trn_br:String//网点
                   )
}
