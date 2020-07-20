package com.elianda.test5

import java.util.logging.SimpleFormatter

import breeze.signal.OptWindowFunction.User
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._

/**
  * @author kylinWang
  * @data 2018/12/26 14:04
  *
  */


//对于分区表的测试; 对于新建表的分区，依然有问题，暂时先放一放
object ConnectHive {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("xxx")
      .config("spark.sql.catalogImplementation","hive")
      .getOrCreate()

     // .enableHiveSupport().getOrCreate()
//

    //spark保存数据；hive连接外部
   // spark.sql("create database if not exists testSql")
    spark.sql("show databases").show
    spark.sql("use default")
    spark.sql("show tables").show
    spark.sql("drop table if exists person3")

    spark.sql("set hive.exec.dynamic.partition=true")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")

/*    spark.sql("create table  person3(id int, name string) partitioned by (dt string) stored as orc")
    //动态分区只对表中的数据进行
    spark.sql("insert into person3 values(1, 'lisi8','20181108')")
    spark.sql("insert into person3 values(1, 'lisi7','20181107')")
    spark.sql("insert into person3 values(1, 'lisi6','20181107')")
    spark.sql("select * from person3").show
    spark.sql("insert into person3 values(5, 'lisi7','20181108')")
    println("==========first show=================")
    spark.sql("select * from person3").show*/

/*
    //思路一：
  //  不能直接覆盖写
 //   spark.sql("insert overwrite table person3 values(2,'深圳')")
  //  如果用DS/DF的方式，用保存的方法实现呢
    val tmp_table = spark.sparkContext.parallelize(Array(User3(1,"北京","20181107"),User3(2,"深圳","20181107")))

    import spark.implicits._
    //连接hive并写入;这里怎么连接hive ： saveasTable 与动态分区不兼容
    //将tmp_table动态分区保存
    val getDf = tmp_table.toDF.coalesce(1).write.mode(SaveMode.Overwrite).format("orc").partitionBy("dt").save("tmp")


//   从持久化到本地的路径获取文件.format("orc")  ; 关于输入输出的格式
    spark.read.format("orc").load("tmp").write.mode(SaveMode.Append).saveAsTable("default.person3")
    */

//思路二：
    import spark.implicits._
    val tmp_table = spark.sparkContext.parallelize(Array(User3(1,"北京","20181107"),User3(2,"深圳","20181107"))).toDF
    val tmp_table2 = spark.sparkContext.parallelize(Array(User3(1,"北京","20181108"),User3(2,"深圳","20181108"))).toDF
    //放入临时表
    tmp_table2.createOrReplaceTempView("tmp_table")

    //这里做一个判断
    //假如表已经存在
    val tables = spark.sql("show tables") //这里是一个DF
    tables.show(false)
    val isTable = tables.filter(col("tableName")==="person3") //只显示tmp_table
    isTable.show(false)
    println("================"+isTable.count())
    if(isTable.count==0){
      println("不存在这个表")
      tmp_table.write.mode(SaveMode.Overwrite).saveAsTable("default.person3")
     // spark.sql("alter table default.person3 add partitioned by (dt string)")
      spark.sql("alter table default.person3 add partition(dt='20181107')") //只要是hive就不能saveastable
      spark.sql("select * from person3").show()
      spark.sql("show tables").show()
    }else{
      println("存在，直接插入")
      spark.sql("insert overwrite table person3 partition(dt) select * from tmp_table")
      spark.sql("select * from person3").show()
      spark.sql("show partitions person3")
    }


  //  spark.sql("select * from person3").show

    //删除tmp
   // spark.emptyDataFrame.write.mode(SaveMode.Overwrite).save("/tmp")

  }

}
case class User3(id:Int , name :String,dt:String)