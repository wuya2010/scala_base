package com.elianda.test1

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.{Column, Dataset, KeyValueGroupedDataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}


object getRangeBetween {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val rdd = sc.parallelize(Array(User4("lisi1", 12, 100, "2018-03-20"), User4("lisi1", 15, 200, "2018-03-15"), User4("lisi1", 15, 300, "2018-03-19"),
    User4("lisi1", 12, 100, "2018-03-10"), User4("lisi1", 20, 100, "2018-02-20")))

    val getdf = rdd.toDF

/*
    rangBetween 是对 orderBy 里面的数定义取值范围， int 类型直接进行加减
 */
    getdf.withColumn("temp",sum(col("add"))over(Window.partitionBy(col("name")).orderBy(col("age").desc)
        .rangeBetween(lit(-5),currentRow())))//获取当前行 拍寻字段的前多少范围内的值
//      .show(false)

//      +-----+---+---+----------+----+
//      |name |age|add|date      |temp|
//       +-----+---+---+----------+----+
//       |lisi1|20 |100|2018-02-20|100 |
//      |lisi1|15 |200|2018-03-15|600 |
//      |lisi1|15 |300|2018-03-19|600 |
//      |lisi1|12 |100|2018-03-20|700 |
//      |lisi1|12 |100|2018-03-10|700 |
//      +-----+---+---+----------+----+

    val temp_ds = getdf.withColumn("date", to_date(col("date")))
    println(temp_ds.schema)

    /*
      对 时间格式的 column 进行加减计算，rangBetween 采用 lit 方式进行处理
     */

    temp_ds
      .withColumn("temp",sum(col("add"))over(Window.partitionBy(col("name")).orderBy(col("date").desc)
//      .rangeBetween(lit(-10),currentRow())))//当前日期的后10天 -10  ;
      .rangeBetween(currentRow(),lit(5))))//date前5天
//      .rangeBetween(lit(0),lit(10))))//固定日期,前10天内
      .show(false)



  }

  case class User4(name: String, age: Int, add: Int, date: String)
}