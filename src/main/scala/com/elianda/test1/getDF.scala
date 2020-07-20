package com.elianda.test1

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.lit
import org.apache.spark.sql.{Column, Dataset, KeyValueGroupedDataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

object getDF {

  def main(args: Array[String]): Unit = {

   val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
   val sc = new SparkContext(conf)
   sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val rdd = sc.parallelize(Array(User3("lisi1",12,100,30),User3("wangwu",15,200,30),User3("lisi0",15,300,30),
      User3("lisi0",12,400,30),User3("lisi3",20,500,30)))

    println("=====test================")
    rdd.foreach(println(_))
  //rdd转df
    val getdf = rdd.toDS

    val v_group: KeyValueGroupedDataset[Int, User3] = getdf.groupByKey(str => str.age)

     val t2 = v_group.mapGroups{case (str,dataIt) =>{
       val temp = dataIt.toList
       temp
      }}

    t2.foreach(println(_))

    //分组之后得到的结果
//    List(User(lisi3,20,北京))
//    List(User(wangwu,15,深圳), User(lisi0,15,深圳))
//    List(User(lisi1,12,北京), User(lisi0,12,深圳))

    val t3 = v_group.mapGroups{case (str,dataIt) =>{
      val temp = dataIt.toList
      val name = temp.map(_.name).head
      name
    }}

    t3.foreach(println(_))

//    wangwu
//    lisi3
//    lisi1



//    //测试 pivot
//   val getdf2 =  getdf.groupBy(col("name"),col("age"))
//
//     .pivot("add",Seq("北京","深圳")).agg(
//     //first(col("name") as "first_name"),
//     //根据add分区
//     first("age") as "age" //这一列不存在
//   ).withColumn("new",row_number() over(Window.partitionBy(col("name")).orderBy(col("age").desc)))
//    .filter(col("new")===1)//.drop("row")
//    getdf2.show()





   // , lit("") as "address"
/*    val fields:Seq[Column]=Seq(col("name"),
      col("age"),
      lit("") as "test")

    //select(cols: Column*)
    getdf.select(fields:_*).show()*/

/*
    12.25  测试去除多余的 “——”

    val getdf2= getdf
      .withColumn("test",lit(""))
      .withColumn("test2",lit(""))
      .withColumn("CADD_ADD", ltrim(concat_ws("_", col("test"),col("test2"), col("name"),
      col("age")),"_"))
*/

    getdf.show(false)
    val groupCols= Seq(col("name"))
//    val sumCol = getCols.map(_.toString()).map(str=>(str,"sum")).toMap
//    getdf.groupBy("name").agg(sumCol).show(false)


    val getds = rdd.toDS()
    getds.show(false)
    val mapCols =Map("sum"->Seq(col("age")),"count"->Seq(col("other")),"avg"->Seq(col("add")))
    val newCols = Seq("new_sum","new_count","new_max")
    aggMix(getds,mapCols,null,groupCols).show(false)


/*
     聚合函数中，取row = 1
     .agg(
        //展开后03_CADD_ADD,03_OI14_AD_POSTCODE,04_CADD_ADD,04_OI14_AD_POSTCODE,08_CADD_ADD,08_OI14_AD_POSTCODE
        first("CADD_ADD") as "CADD_ADD", //地址4+3+2+1
        first("OI14_AD_POSTCODE") as "OI14_AD_POSTCODE",//邮编
        //根据OI14_APPG_DATE 倒叙排列， 取row =1
        row_number() over(Window.orderBy(col("OI14_APPG_DATE").desc)) as "OI14_APPG_DATE"
      ).filter(col("OI14_APPG_DATE")===1).drop("OI14_APPG_DATE")*/

  }

  def aggMix(ds:Dataset[_],aggCols:Map[String,Seq[Column]],colNames:Seq[String],groupCols:Seq[Column]=null)= {

    val sumCols = aggCols.getOrElse("sum",Seq(lit(""))).map(_.toString()).map(str=>(str,"sum")).toMap
    val countCols = aggCols.getOrElse("count",Seq(lit(""))).map(_.toString()).map(str=>(str,"count")).toMap
    val maxCols = aggCols.getOrElse("max",Seq(lit(""))).map(_.toString()).map(str=>(str,"max")).toMap
    val minCols = aggCols.getOrElse("min",Seq(lit(""))).map(_.toString()).map(str=>(str,"min")).toMap
    val avgCols = aggCols.getOrElse("avg",Seq(lit(""))).map(_.toString()).map(str=>(str,"avg")).toMap

    def getMap(mapCols:Seq[Map[String,String]])={
      var aggmap:Map[String,String]=Map.empty
      val filtermap = mapCols.map(_.map{case (key,value)=>{
        if(key.equals("")){
          aggmap :: Nil
        }else{
          aggmap += (key->value)
        }}})
      aggmap
    }

    val filterMap = getMap(Seq(sumCols,countCols,maxCols,minCols,avgCols))

    //实现重命名规则
    def renameCols(aggDs:Dataset[_],colNames:Seq[String],groupCols:Seq[Column]=null)={
      if(colNames != null){
        val aggCols = aggDs.columns.toSeq.map(col)
        //如果没有groupcols 会报错
        val newNameCols = if(groupCols == null){
          colNames
        }else{
          groupCols.map(_.toString) ++ colNames
        }
        val zipCols = aggCols zip newNameCols map (x => x._1.alias(x._2))
        for(i<-1 to 2){
          println("***********************************************")
        }
        println("聚合函数字段与重命名对应关系：")
        (aggCols zip newNameCols).map(x=>x._1.toString() + "  ->  " + x._2).foreach(println)
        for(i<-1 to 2){
          println("***********************************************")
        }
        aggDs.select(zipCols:_*)
      }else{
        aggDs
      }
    }

    if (groupCols == null) {
      val aggDs = ds.agg(filterMap)
      renameCols(aggDs,colNames)
      aggDs
    } else {
      val groupAggDs = ds.groupBy(groupCols: _*).agg(filterMap)//.schema .zip colNames map (x => x._1.alias(x._2))
      renameCols(groupAggDs,colNames,groupCols)
    }
  }
}
case class User1(name:String,age:Int)
case class User(name:String,age:Int,add:String)
case class User2(name:String,age:Int,add:String,other:String)
case class User3(name:String,age:Int,add:Int,other:Int)