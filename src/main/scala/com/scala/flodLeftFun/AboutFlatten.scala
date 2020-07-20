package com.scala.flodLeftFun

import com.elianda.test1.User
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/4/18 17:11
  *
  */
object AboutFlatten {

  def main(args: Array[String]): Unit = {
    val list = Seq(Seq(1,2),Seq(3,4))

    println(list.flatten) // List(1, 2, 3, 4)

    val list2 = list.flatMap(x=>x.map(_*2))

    println(list2)  //List(2, 4, 6, 8)

    println("=========测试===============")
    //测试2个表join后的空字段
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    val df1 = sc.parallelize(Array(User("lisi0",1,"深圳"), User("lisi0",2,"深圳"))).toDF

    val df2 = sc.parallelize(Array(User("lisi0",2,"深圳"), User("lisi0",3,"深圳"))).toDF().select(col("name") as "new_name",col("age"),col("add") as "new_add")

    val map = Map("new_name"->"xiaomi","new_add"->"shenzhen")

    df1.join(df2,Seq("age"),"left").na.fill(map).show(false)

  }
}
