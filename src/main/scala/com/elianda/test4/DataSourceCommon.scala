package com.elianda.test4

import java.io.File
import java.nio.CharBuffer
import java.util.Date

import com.elianda.test3.BASE_CONFIG
import org.apache.spark.sql.{Column, SparkSession}
import org.slf4j.LoggerFactory
import org.apache.spark.sql.functions._
import org.apache.spark.storage.StorageLevel

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * @author kylinWang
  * @data 2018/12/25 14:39
  *      从json中获取数据
  *
  */
trait DataSourceCommon {
 /* val logger = LoggerFactory.getLogger(this.getClass)

  def getDSByConfig(spark:SparkSession,date:Date,bc:BASE_CONFIG)=getDSByConfig(spark,date,bc,lit(true))

  def getDSByConfig(spark:SparkSession, date:Date, bc:BASE_CONFIG, filterExpr:Column)=bc.file_type match{
    case "orc" =>  getDSByOrc(spark, date, bc, filterExpr)
    case  _ => null
  }


  //根据指定的路径获取数据; json
  def getDSByOrc(spark: SparkSession, date: Date, bc: BASE_CONFIG, filterExpr: Column) = {
    //对路径进行  "/oramis/dt/BANCS_INVS_I"
    val path = bc.file_path.split(",").map(str=>str.replace("/dt/","/"+date+"/"))
    logger.info(s"获取源数据${bc.tb_name}")
    //自带的shema
    if(bc.header) logger.info("orc文件自带schema,不使用外部schema")

    //json信息放到从json获取信息与本地ddl的比对，根据映射关系，将数据放入
    //获取每天最新的DDL  config/DDL
    val ods_config =LRUCache[String, String](200)
    val lines = Source.fromFile(new File("config\\DDL\\ddl_relm.conf")).getLines()
    var map = Map[String,String]()
    //将数据放入map中; 过滤掉非空的
    lines.filter(!_.trim.equals("")).foreach(str=>{
      map += str.split("=")(0)->str.split("=")(1)
    })
    //获取了map对应的ddl的ddl文件名
    val relation_ddl = map.getOrElse(bc.tb_name,null)
    //获取了ddl的数据
    val ods_ddl = Source.fromFile(new File(s"config\\DDL\\${relation_ddl}")).getLines()
   //获取ddl的schema信息,获取字段名信息
    val ddl_schema  = new ArrayBuffer[String]
    //获取所有的头字段信息
    ods_ddl.foreach(str=>
       ddl_schema += str.split("\\s")(0).toUpperCase
    )

    //将json中的信息，写入到configMap2中，只取cols 与 schema 的字段; get之后写到本地
   // val ddl_json = Source.fromFile(new File("E:\ChinaBank\first_project - test\src\main\resources\base4.json")).getLines()

    //bc是配置文件, 获取数据后写到map ; 键值对写入到map中
    val chema_type_Map  = bc.schema.split(",").map(str=>(str.split(" ")(0)->str.split(" ")(1))).toMap[String,String]

    //把前后的信息做一个对比,map，如果有就用ddl, 没有null ; 取2个参数做比对
    // schema_type_Map.map { case (colName, colType) => if (cols.contains(colName)) col(colName) else lit(null).cast(colType) as colName }.toArray
    val  model_schema = chema_type_Map.map{
      //用偏函数
      case (colName,colType) =>if(ddl_schema.contains(colName)) col(colName) else lit(null).cast(colType) as colName
    }.toArray

    //从原表中获取 path:Array[String]
    val ds_cache = spark.read.format("orc").load(path:_*).toDF(relation_ddl).select(model_schema:_*)

   ds_cache.persist(StorageLevel.MEMORY_AND_DISK)


  }*/
}
