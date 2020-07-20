package com.elianda.test6

import com.elianda.test1.Usertest
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/1/6 15:06
  *
  */


//针对2个表的join进行测试，最终的分区结果
object testPartiton2 {

  def main(args: Array[String]): Unit = {

    //查看分区数
//    val conf = new SparkConf().setAppName("xxx").setMaster("local[*]")
//    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().master("local[*]")
        .config("spark.sql.shuffle.partitions",100).appName("xxx").getOrCreate()
    val sc = spark.sparkContext

    val rdd1 = sc.parallelize(Array(
      Usertest("001",1,"西安1","20180101"),
      Usertest("002",1,"深圳1","20180101"),
      Usertest("003",1,"上海1","20180101"),
      Usertest("004",1,"广州1","20180101"),
      Usertest("005",1,"北京1","20180101")))

    val rdd2 = sc.parallelize(Array(
      Usertest2("001",2,"西安2","20180102"),
      Usertest2("002",2,"深圳2","20180102"),
      Usertest2("002",2,"深圳2","20180102")))

    println("=============获取分区数==================="+rdd1.getNumPartitions)//默认分区数，是核数 12
    println("=============获取分区数==================="+rdd2.getNumPartitions)

    import spark.implicits._
    val df1 = rdd1.toDF() //可以得到分区数 6
    val df2 = rdd2.toDF() // 4个

    println("====================df1==============="+df1.rdd.getNumPartitions)
    println("====================df12==============="+df2.rdd.getNumPartitions)

    //打印输出df的分区
    val getPartition1 = df1.repartition(10).rdd.glom().collect()
    for(part <- getPartition1){
        println(part.getClass.getName + "::::::::" + part.length)  //每个patiton的数据量
    }

    val getPartition2 = df2.repartition(5).rdd.glom().collect()
    for(part <- getPartition2){
      println(part.getClass.getName + "::::::::" + part.length)  //每个patiton的数据量
    }


/*
情况1：
rdd不设置的情况下， 默认是核数为分区数
join后的rdd分区数位 200 ， 可以通过repartition进行设置分区数

情况2：
设置rdd1,rdd2的分区数，后默认分区数的改变
 */

    //数据量小，2个表join后的分区
    val df3 = df1.join(df2,Seq("name"),"left")//.repartition(300,col("name")) // 默认只有4个，是根据key的hash值进行分区
    println(df3.rdd.toDebugString)

    println("====================df3==============="+df3.rdd.getNumPartitions) //默认分区数 200,
    val getPartition3 = df3.rdd.glom().collect()
    var i = 0
    for(part <- getPartition3){
      println(part.getClass.getName + "::::::::" + part.length)  //会有很多空
      i = i + 1
      println("个数"+ i )
    }


   // df3.write.format("json").mode(SaveMode.Overwrite).partitionBy("name").save("e://test")//partitionBy：生成的文件夹个数


    //coalesce ： 默认是5个，改写后生成2个 ， 减小文件生成个数
//    df3.coalesce(2).write.format("json").mode(SaveMode.Overwrite).save("e://test")//partitionBy：生成的文件夹个数

    //join后的分区个数： 5个 ，repartition 多生成文件个数，会有shuffle ; 会有不均匀的情况

    //输出文件个数与分区的个数的关系
    df3.write.format("json").mode(SaveMode.Overwrite).save("e://test//join")//partitionBy：生成的文件夹个数
    df2.write.format("json").mode(SaveMode.Overwrite).save("e://test//df2")//partitionBy：生成的文件夹个数
    df3.show()
    //重分区

    //重新定义分区 , 定义分区字段

    Thread.sleep(100000000)


  }

}


//定义一个类
class getTest2(num:Int) extends Partitioner{
  override def hashCode(): Int = super.hashCode()

  override def equals(obj: Any): Boolean = super.equals(obj)

  override def numPartitions: Int = num

  //定义分区规则,根据奇偶分区，并设置分区数
  override def getPartition(key: Any): Int = {
/*    val key2 = key.asInstanceOf[Int]
    //返回值是key2的
    key2 % 2.abs*/
    0
  }

}

case class Usertest2(name:String,age2:Int,add2:String,etl_date2:String)