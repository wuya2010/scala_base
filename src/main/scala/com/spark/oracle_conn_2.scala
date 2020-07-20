package com.spark

import java.sql.{DriverManager, PreparedStatement}
import java.util.Properties

import org.apache.spark.sql.types.{DataType, IntegerType, LongType, StringType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SparkSession}

/**
  * @author kylinWang
  * @data 2018/7/2 9:45
  *
  */
object oracle_conn_2 {
  def main(args: Array[String]): Unit = {

    //连接oracle 方式一
    val conf = new SparkConf().setMaster("local[*]").setAppName("oracle2")
    val sc = new SparkContext(conf)
    //    val spark = SparkSession.builder().master("local[*]").appName("oracle").getOrCreate()
    val spark = SparkSession.builder().config(conf).getOrCreate()

    //将ds数据插入到oracle
    val  rdd = sc.parallelize(Array(Gzg(1,"wang"),Gzg(2,"he"),Gzg(3,"mao"),Gzg(2,"wang"),Gzg(3,"wang"),Gzg(4,"wang")))
    import spark.implicits._
    val ds = rdd.toDF("id","name").as("Gzg")

    // 将增加和删除分写为2个方法

    //将数据写入Oracle
    val prop = new Properties()
    prop.load(ClassLoader.getSystemResourceAsStream("oracleconn.properties"))

    //删除对应etl_date下的数据
    def preparedStatSetter(preparedStat: PreparedStatement, row: Row, dataType: DataType, index: Int)= dataType match {
      case IntegerType => preparedStat.setInt(index + 1, row.getInt(index))
      case LongType => preparedStat.setLong(index + 1, row.getLong(index))
      case StringType => preparedStat.setString(index +1, row.getString(index))
      case _ => throw new Exception(s"数据格式不合法${dataType}")
    }


    val table = "test" //gzg_tabletest
    val url = prop.getProperty("url")
    val countNums = ds.count()
    val columns = ds.schema.map(_.name).mkString(",")
    val fillValues = ds.schema.map(_=>"?").mkString(",")
    val dataTypes = ds.schema.map(_.dataType)
    val batchSize = 100
    val name="wang"
    val sql = s"insert into ${table}(${columns}) values (?,?)"

    //插入数据并校验, 如果有插入异常呢

    ds.foreachPartition(partition=> {
      val conn = DriverManager.getConnection(url, prop)
      val stat = conn.prepareStatement(sql)
      conn.setAutoCommit(false) //默认是自动提交

      var i = 0
      var notInsertNums = countNums
      //在每一个分区插入数据
      try{
      partition.foreach(row => {
        //循环插入数据，一次插入一条 ; 获取index
        dataTypes.zipWithIndex.foreach { case (dataty, index) => {
          preparedStatSetter(stat, row, dataty, index) //传入index
        }
        }

        stat.addBatch()
        i += 1
        if (i >= batchSize) {
          val nums = stat.executeBatch()
          notInsertNums -= nums.size //得到没有加载的
          conn.commit()
          stat.clearParameters()
          i = 0
        }
      })

      //还没有插入的数据
      if (notInsertNums > 0) {
        stat.executeBatch()
        conn.commit()
        stat.clearBatch()
      }


    } catch{ case e:Exception =>
        conn.rollback()
        throw new Exception("插入数据出错,数据回滚...")
     }
    finally{
      stat.close()
      conn.close()
    }
    })


  }

  case class Gzg(id:Int, name:String)
}
