package com.spark

import java.io.{File, FileInputStream}
import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util.Properties

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * @author kylinWang
  * @data 2018/6/30 10:04
  *
  */
object oracle_conn {

  def main(args: Array[String]): Unit = {

    //连接oracle 方式一
    val conf = new SparkConf().setMaster("local[*]").setAppName("oracle2")
    val sc = new SparkContext(conf)
//    val spark = SparkSession.builder().master("local[*]").appName("oracle").getOrCreate()
    val spark = SparkSession.builder().config(conf).getOrCreate()
    //加载某一个表
    val ds_1 = spark.read
          .format("jdbc")
          .option("driver", "oracle.jdbc.driver.OracleDriver")  // 这个jar怎么添加
          .option("url", "jdbc:oracle:thin:@127.0.0.1:1521:orcl")
          .option("user", "root")
          .option("password", "123456")
          .option("dbtable", "TEST")
          .load


   //将ds数据插入到oracle

    val  rdd = sc.parallelize(Array(1,2,3,4,5,6))
    import spark.implicits._
    val df = rdd.toDF("id")

//    df.show()

    val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
    val userName = "root"
    val passWd="123456"
    val dbtable ="TEST"

    //每次吸入用覆盖的方式
    val sql = s"insert into ${dbtable} values(?)"

    df.foreachPartition(partition => {
      val conn = DriverManager.getConnection(url, userName, passWd)
      val state = conn.prepareStatement(sql)
      partition.foreach(df=>{
        state.setInt(1,df.getInt(0))
        state.addBatch()
      })
      state.executeBatch()
      state.close()
      conn.close()
    })



    // 连接oracle 方式2
    val prop= new Properties()
    val fis = new FileInputStream(new File("E:\\ChinaBank\\first_project - test\\src\\main\\resources\\oracleconn2.properties"))
    prop.load(fis)

   //读取配置文件






  }

}
