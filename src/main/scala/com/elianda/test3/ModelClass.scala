package com.elianda.test3
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/12/24 16:43
  *
  */
object ModelClass {

}

case class Cols(
                 name:String,
                 alias:String,
                 context:String)

case class CACHE(
                  enable: Boolean,
                  level: Int,
                  name: String //缓存的名称
                )

//这个样例类这么写
case class REPARTION(
                    enable: Boolean,
                    numPartition:Int,
                    cols:String
                    ){
  def getCols()={
    cols.split(",").map(col(_))
  }
}


case class BASE_CONFIG(
  tb_name: String, //表名
  file_type: String, //文件类型，csv,txt,orc
  file_sep: String, //文件分割符
  file_path: String, //文件路径,多个路径逗号分隔
  header: Boolean, //文件是否有头标题
  interSchema: Boolean, //文件是否自动判定字段类型
  options:String,
  url:String,//jdbc数据源使用
  jdbc_tb_name:String,//jdbc数据源使用
  jdbc_username:String,//jdbc数据源使用
  jdbc_password:String,//jdbc数据源使用
  schema: String, //schema  spark.read.schema("a INT, b STRING, c DOUBLE").csv("test.csv")
  filter:String,
  cols: Array[Cols], //输出的字段
  cache:CACHE,
  repartion:REPARTION//分区条件
 )
