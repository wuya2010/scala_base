package com.elianda.test1

import java.text.SimpleDateFormat

import org.apache.commons.lang3.time.FastDateFormat
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit, when}

/**
  * @author kylinWang
  * @data 2018/1/13 11:41
  *
  */
object ChooseFunTest {
  def main(args: Array[String]): Unit = {

    val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val rdd01 = sc.parallelize(Array(
      Usertest("001",1,"西安1","20180101"),
      Usertest("002",1,"深圳1","20180101"),
      Usertest("003",1,"上海1","20180101"),
      Usertest("004",1,null,"20180101"),
      Usertest("005",1,"北京1","20180101")),10)

    val rdd02 = sc.parallelize(Array(
      Usertest("001",1,null,"2018-01-01"),
      Usertest("002",2,"深圳2","2018-01-01"),
      Usertest("003",5,null,"20180101"),
      Usertest("004",2,"广州2","20180101"),
      Usertest("005",0,"北京2","20180101")),5)
    //rdd转df
    val info_01 = rdd01.toDF.alias("history")
    val info_02 = rdd02.toDF.alias("new")

    val test = info_02.select(coalesce(col("new.add"),lit("1")) as "test",
      col("new.add").contains("1") as "boolean"
    )
    test.show(false)


    val getUnionDs = info_01.join(info_02,Seq("name"),"left")

    getUnionDs.select(
      when(col("new.add").isNotNull , lit("1")).otherwise(lit(0))
      ).show(false)

    val getStateDs  = getUnionDs
      .withColumn("add_state",
        //判断add不为空   Column===  : 对某一列内容的处理
//        if(getUnionDs.select(col("new.add")) != null){
        if(when(col("new.add").isNotNull,true).otherwise(false)==true){
          //具体判断列与  还是没有解决？
          lit("1")
          // when(col("new.add") =!= col("history.add"),lit("addChange_true")).otherwise(lit("addChange_false"))
         }else{
          lit("0")
        //  when(col("history.add").isNotNull,lit("addAdd_true")).otherwise(lit("addAdd_false"))
        }) //假如身份证号码为空，判断是否新增
        .withColumn(
      "test",{
        val a = when(col("new.add") =!= col("history.add"),lit("addChange_true")).otherwise(lit("addChange_false"))
        val b = when(col("new.add").isNotNull,lit("addAdd_true")).otherwise(lit("addAdd_false"))
        when(col("history.add").isNotNull,a).otherwise(b)
      })
        .withColumn(
          "boss_change_time",
          when(col("test").contains(true) ,lit("1")).otherwise(lit(null))
        )
        .withColumn("new_date",
          {
            val new_col = concat_ws("-",substring(col("new.etl_date"),1,4),substring(col("new.etl_date"),5,2))
            date_format(col("new.etl_date"), "yyyyMM")
          }
        )


//      .withColumn("name_state",
//      if((col("new.name")!=lit(null))){
//        when(col("new.name")=!=col("history.name"),lit("nameChange_true")).otherwise(lit("nameChange_false"))
//      }else{
//        when(col("history.name").isNotNull,lit("nameAdd_true")).otherwise(lit("nameAdd_false"))})  //假如身份证号码为空，判断是否新增

//      //得到状态
//      .withColumn("state",
//      when(col("add_state").contains("true") && col("name_state").contains("true"),lit("true")).otherwise("false")
//    )


    //判断姓名是否变化 CI06_D4_BOSS_NAME
//      .withColumn("name_state",
//      //判断姓名如果不为空，判断是否修改
//      if(col("new.CI06_D4_BOSS_NAME").isNotNull){
//        when(col("new.CI06_D4_BOSS_NAME")=!=col("histroy.CI06_D4_BOSS_NAME"),lit("nameChange_true")).otherwise(lit("nameChange_false")) as "nameChange"
//      }else{
//        when(col("histroy.CI06_D4_BOSS_NAME").isNotNull,lit("nameAdd_true")).otherwise(lit("nameAdd_false")) as "nameAdd"})
//      .withColumn("state",
//        when(col("id_state") && col("name_state") == true,lit("true")).otherwise("false")
//      )

    getStateDs.show(false)

  }

}
