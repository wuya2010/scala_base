package com.elianda.test5

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * @author kylinWang
  * @data 2018/12/26 15:54
  *
  */
object ConnectHive2 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("xx")
      .config("spark.sql.parquet.writeLegacyFormat",true)
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._

    //创建DF
    val data = Array(("001", "张三", 21, "2018"), ("002", "李四", 18, "2017"))
    val df = spark.createDataFrame(data).toDF("id","name","age","year")
    df.createOrReplaceTempView("tep_table")

    spark.sql("set hive.exec.dynamic.partition.mode=nonstatic")
    spark.sql("set hive.exec.dynamic.partition=true")
    spark.sql("use default")

    //在hive创建表new_table
 /*   spark.sql(
      """
        |create table test_partition (
        |id string comment 'ID',
        |name string comment '名字',
        |age int comment '年龄'
        |)
        |comment '测试分区'
        |partitioned by (year int comment '年')
        |ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' ;
      """.stripMargin)*/

    //将df保存
    df.write.mode(SaveMode.Overwrite).format("Hive").partitionBy("year").saveAsTable("test_partition")
    spark.sql("insert into test_partition select * from tep_table")

    spark.stop

  }

}
