package com.elianda.test7

import com.elianda.test1.User
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Column, SparkSession}
import org.apache.spark.sql.catalyst.expressions.Upper
import org.apache.spark.sql.functions._

/**
  * @author kylinWang
  * @data 2018/2/14 11:58
  *
  */
object test_new_fun {

  def main(args: Array[String]): Unit = {
    val conf =  new SparkConf().setMaster("local[*]").setAppName("xx")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val spark = SparkSession.builder().config(conf).getOrCreate()

    import spark.implicits._
    import org.apache.spark.sql.functions._

    val rdd = sc.parallelize(Array(test("420984198709056679"),test("4209841987090x6"),test("420984198709056"),
      test("H0021449202"),test("m341567x9"),test("11870905661")))
//    val rdd = sc.parallelize(Array(test("420984198709056")))

    val getdf = rdd.toDF()
    getdf.show(false)
//    getdf.select(getDocuments(col("num"))).show(false)
    getdf.select(getDocuments(trim($"num"))).show(false)

//   when(col("Y4_VALUE")===0,lit(0)).otherwise(round(col("Y3_VALUE")-col("Y4_VALUE"))/col("Y4_VALUE"),3)
  }


  def getDocuments(getCol:Column)={
    //获取参数列的信息
//    val getCol = col(s"${colName}")
    //判断条件
//    val test = regexp_replace(getCol,"[0-9]","")
//    when(test === "" ,null).otherwise(test)

    val isID = length(getCol) === 15 and substring(getCol,1,1) =!= "0" and  when(regexp_replace(getCol,"[0-9]","")==="",null).otherwise(getCol).isNull
    val isHK_1 = length(getCol) === 9 and substring(getCol,1,1).isin("H","M","m","h") and when(regexp_replace(substring(getCol,2,8),"[0-9]","")==="",null).otherwise(getCol).isNull
    val isHK_2 = length(getCol) === 11 and substring(getCol,1,1).isin("H","M","m","h") and  when(regexp_replace(substring(getCol,2,10),"[0-9]","")==="",null).otherwise(getCol).isNull
    val isHK = isHK_1 or isHK_2


//    val isID = length(getCol) === 15 and substring(getCol,1,1) =!= "0"// and  ltrim(substring(getCol,"0123456789").isNull
//    val isHK_1 = length(getCol) === 9 and substring(getCol,1,1).isin("H","M","m","h") //and ltrim(substring(getCol,2,8),"0123456789").isNull
//    val isHK_2 = length(getCol) === 11 and substring(getCol,1,1).isin("H","M","m","h") //and ltrim(substring(getCol,2,10),"0123456789").isNull
//    val isHK = isHK_1 or isHK_2

    //获取判断后的值
    val temp = substring(getCol,1,1).cast("int")*7 +
      substring(getCol,2,1).cast("int")*9 +
      substring(getCol,3,1).cast("int")*10 +
      substring(getCol,4,1).cast("int")*5 +
      substring(getCol,5,1).cast("int")*8 +
      substring(getCol,6,1).cast("int")*4 +
      substring(getCol,7,1).cast("int")*6 +
      substring(getCol,8,1).cast("int")*3 +
      substring(getCol,9,1).cast("int")*7 +
      substring(getCol,10,1).cast("int")*9 +
      substring(getCol,11,1).cast("int")*10 +
      substring(getCol,12,1).cast("int")*5 +
      substring(getCol,13,1).cast("int")*8 +
      substring(getCol,14,1).cast("int")*4 +
      substring(getCol,15,1).cast("int")*2
    val getID_1= when(temp % 11 === 0,"1").
      when(temp % 11 === 1,"0").
      when(temp % 11 === 2,"X").
      when(temp % 11 === 3,"9").
      when(temp % 11 === 4,"8").
      when(temp % 11 === 5,"7").
      when(temp % 11 === 6,"6").
      when(temp % 11 === 7,"5").
      when(temp % 11 === 8,"4").
      when(temp % 11 === 9,"3").
      when(temp % 11 === 10,"2")

    //不同列的字符串拼接
    val getID =  concat(substring(getCol,1,6), lit("19"), substring(getCol,7,9),getID_1) //+ getID_1
    val getHK =  substring(upper(getCol),1,9)
    //添加逻辑进行计算，返回列信息
    when(isID,getID).when(isHK,getHK).otherwise(getCol) as "new_id"
  }

}

case class test(num:String)