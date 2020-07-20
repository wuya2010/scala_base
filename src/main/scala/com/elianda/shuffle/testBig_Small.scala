package com.elianda.shuffle

import java.sql.Timestamp

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.Decimal
import org.json4s.native.Json

import scala.util.parsing.json.JSONObject


/**
  * @author kylinWang
  * @data 2018/1/16 9:52
  *
  */

//读取打标数据，进行join，模拟数据倾斜的过程
object testBig_Small {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("xx").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate()
    sc.setLogLevel("WARN")

    //解析字段，进行聚合处理
    val rdd_pay = sc.textFile("E:\\Codes\\04-project\\01-项目\\离线\\2-离线\\代码及日志\\coursepay.log")
    val rdd_shopping = sc.textFile("E:\\Codes\\04-project\\01-项目\\离线\\2-离线\\代码及日志\\courseshoppingcart.log")

    println(rdd_pay.first())//rdd的first算子
    println(rdd_shopping.first())
    import spark.implicits._

//    //过滤出为true的部分
//    val ttt = rdd_pay.filter(data => {
//      val trans_data = ParsejsonData.getJsonData(data)
//      !trans_data.isInstanceOf[JSONObject]
//    })
//    println(ttt.first())

    val df_pay = rdd_pay.filter(data => {
      val trans_data = ParsejsonData.getJsonData(data)
      !trans_data.isInstanceOf[JSONObject]
    }).mapPartitions(partitions => {
      //对json对象进行解析
      partitions.map { data => {
        val jsonObject = ParsejsonData.getJsonData(data)
        val orderid = jsonObject.getString("orderid")
        val paymoney = jsonObject.getString("paymoney")
        val discount = jsonObject.getString("discount")
        val createtime = jsonObject.getTimestamp("createtime")
        val dt = jsonObject.getString("dt")
        val dn = jsonObject.getString("dn")
        (orderid, paymoney, discount, createtime,dt,dn)
      }}
    }).toDF("orderid", "paymoney", "discount", "createtime","dt","dn")

    df_pay.show(1,false)

    //2步，过滤掉不是json的； 解析对应的字段
    val df_shopping = rdd_shopping.filter(data => {
      val trans_data = ParsejsonData.getJsonData(data)
      !trans_data.isInstanceOf[JSONObject]
    }).mapPartitions(partition => {
      //对json对象进行解析
      partition.map { data => {
        val json_data = ParsejsonData.getJsonData(data)
        val courseid = json_data.getIntValue("courseid")
        val orderid = json_data.getString("orderid")
        val coursename = json_data.getString("coursename")
        val discount = json_data.getBigDecimal("discount")
        val sellmoney = json_data.getBigDecimal("sellmoney")
        val createtime = json_data.getTimestamp("createtime")
        val dt = json_data.getString("dt")
        val dn = json_data.getString("dn")
        (courseid, orderid, coursename, discount, sellmoney, createtime,dt,dn)
      }}
    }).toDF("courseid", "orderid", "coursename", "discount", "sellmoney", "createtime","dt","dn")

    df_shopping.show(1,false)

    df_pay.cache()
    df_shopping.cache()

    //2个表进行关联，查看是否有数据倾斜
   val pay_shopping = df_pay.join(df_shopping,Seq("orderid"),"left").repartition(20)

    pay_shopping.show(1,false)

    Thread.sleep(100000000L)

  }
}

//获取路径下的文件，组成一个DF
case class shopping_car(courseid:Int,
                        orderid:String,
                        coursename:String,
                        discount:Decimal,
                        sellmoney:Decimal,
                        createtime:Timestamp
                       )

case class course_pay(orderid:String,
                        discount:Decimal,
                        paymoney:Decimal,
                        createtime:Timestamp
                        )

