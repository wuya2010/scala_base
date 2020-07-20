package com.elianda.shuffle

import java.util.Random

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/1/17 15:29
  *
  */

//找到数据倾斜的字段，对倾斜大的字段进行处理
object JoinShuffle2 {
  def main(args: Array[String]): Unit = {

    val number = 10000 //大数据
    val sml = 100  // 小数据
    val defpar = 100
    val skewPart = 7

    val data1 =  for(num <-0 to number) yield (if(num < defpar) num else number+(new Random()).nextInt(skewPart)*(skewPart),num)
    val data2 =  for(num <-0 to sml) yield (if(num< defpar ) num else number+(new Random()).nextInt(skewPart)*(skewPart),num)

    val spark = SparkSession.builder().master("local[5]").appName("xxx")
      // 不设置广播变量等
      //.config("spark.default.parallelism", defpar)
      // .config("spark.sql.autoBroadcastJoinThreshold","1")//1bit : 广播变量值太小
      //.config("spark.sql.autoBroadcastJoinThreshold",s"${100L*1024*1024}")//广播变量值扩大为： 100M
      //.config("spark.sql.shuffle.parttiions",skewPart)
      .getOrCreate()

    //转换为DF
    val dfbig = spark.createDataFrame(data1).toDF("id","value")
    val dfsmall = spark.createDataFrame(data2).toDF("id","value")

    import spark.sqlContext.implicits._
    //找到倾斜的key
    //1.count,获取col("id') 与 count 值  => take(10) 获取一个集合
    // 有可能倾斜得字段有好几个  0.5->1007,10000   0.3->10007,10000  0.2->10042,10021,10028等
    val skewKeys = dfbig.sample(false,0.2).groupBy(col("id")).count().sort(col("id").desc)
      .filter(col("count")>200).collect().map(_.get(0))  //从ds获取第一个字段信息
     // .map(str=>str(0)).toDF()
    //.map(_.get(0))  // 这个的转换关系
    println("=========打印输出倾斜字段============")
    skewKeys.foreach(println)

    //2. 获取倾斜的字段，在字段前面添加随机值  dfsmall ： 过滤第一个倾斜比较大的字段
//   val  skeSmlDf =  dfsmall.filter(col("id").contains(skewKeys(0)))//.toDF("id","value")



//    val  skeSmlDf =dfsmall.filter(col("id").contains(skewKeys=>{
//        for(0 <- skewKeys)
//    })
//    // val  skeSmlDf =  dfsmall.filter(col("id").contains(skewKeys:_*))
//   val  noSkeSmlDf =  dfsmall.filter(!col("id").contains(skewKeys(0)))//.toDF("id","value")
//
//    println("=====================")
//    skeSmlDf.show(false)
//    noSkeSmlDf.show(false)



//
//   //3. 对倾斜字段前面加上随机值
//   val trans_sml = skeSmlDf.map{
//     case (id,value) =>{
//      for(i <- 1 to 100) yield{
//        val prefix = (new Random).nextInt(100)
//        (prefix + "_" + id, value)
//      }}}.toDF("id","value")
//
//   trans_sml.show(false)
//
//   // 4. split rdd
//   val  skeBigDf =  dfbig.filter(col("id").contains(skewKeys(0)))//.toDF("id","value")
//    // val  skeSmlDf =  dfsmall.filter(col("id").contains(skewKeys:_*))
//    val  noSkeBigDf =  dfbig.filter(!col("id").contains(skewKeys(0)))//.toDF("id","value")
//
//    println("=====================")
//    skeBigDf.show(false)
//    noSkeBigDf.show(false)
//
//    //3. 对倾斜字段前面加上随机值
//    val trans_big = skeSmlDf.map{
//      case (id,value) =>{
//        for(i <- 1 to 100) yield{
//          val prefix = (new Random).nextInt(100)
//          (prefix + "_" + id, value)
//        }}}.toDF("id","value")
//
//    trans_big.show(false)
//
//    //分别对倾斜数据和非倾斜数据进行join
//    val skeDf = trans_sml.alias("small")
//      .join(trans_big.alias("big"),Seq("id"),"left")
//      .select()
//    val noskeDf = noSkeSmlDf.alias("a")
//      .join(noSkeBigDf.alias("big"),Seq("id"),"left")
//      .groupBy("id").agg(
//      sum("b.value").alias("total")
//    )
//
//    //合并2者结果
//    skeDf.union(noskeDf).show(20)
//
//    Thread.sleep(100000L)
    spark.stop()

  }
}
