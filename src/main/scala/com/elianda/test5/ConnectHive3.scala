package com.elianda.test5

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
/**
  * @author kylinWang
  * @data 2018/1/9 19:45
  *
  */
object ConnectHive3 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("xxx")
      ///.config("spark.sql.warehouse.dir", "warehouse")
      .config("spark.sql.catalogImplementation","hive")
      .getOrCreate()
     //.enableHiveSupport().getOrCreate()


    spark.sql("show databases").show
    spark.sql("use default")
    spark.sql("drop table if exists person2")
    //设置动态分区 + 非严格模式
    spark.sql("set hive.exec.dynamic.partition=true")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")

    val tmp_table1 = spark.sparkContext.parallelize(Array(User3(1,"北京","20181108"),User3(2,"深圳","20181108")))

    import spark.implicits._
   spark.sql("drop table testpartition ") //不能有配置文件，不然就连接外部hive
   spark.sql("create table testpartition(id int, name string) partitioned by (dt string)") //在hive中设置动态分区

  //需要创建表，并insertintoTable  + 分区字段
   val getDf = tmp_table1.toDF.coalesce(1).write.mode(SaveMode.Overwrite).partitionBy("dt").saveAsTable("testpartiton")

    spark.sql("select * from testpartiton").show

    val tmp_table2 = spark.sparkContext.parallelize(Array(User3(3,"北京","20181109"),User3(4,"深圳","20181109")))
//.partitionBy("dt")
    val getDf2 = tmp_table2.toDF.coalesce(1).write.mode(SaveMode.Overwrite).partitionBy("dt").saveAsTable("testpartiton")

      spark.sql("select * from testpartiton").show

  }

}
