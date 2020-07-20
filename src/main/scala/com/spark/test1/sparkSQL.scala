package com.spark.test1

import java.text.DecimalFormat

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.{UserDefinedAggregateFunction, Window}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
  * @author kylinWang
  * @data 2018/5/30 16:32
  *
  */
object sparkSQL {
  def main(args: Array[String]): Unit = {

    //读取RDD
    val conf = new SparkConf().setAppName("WANG").setMaster("local[*]")
    val ssc = new SparkContext(conf)
    //这里不用new
    val spark = SparkSession.builder().config(conf).getOrCreate()

    //读取数据
    val user_rdd = ssc.textFile("E:\\Codes\\user_visit_action.txt")

    //数据封装进样例类
    import spark.implicits._

    val user_action = user_rdd.map(str => {
      val splits = str.split("\\t")
      //封装样例类
      UserVisitAction(
        splits(0),
        splits(1).toLong,
        splits(2),
        splits(3).toLong,
        splits(4),
        splits(5),
        splits(6).toLong,
        splits(7).toLong,
        splits(8),
        splits(9),
        splits(10),
        splits(11),
        splits(12).toLong)
    })


    val user_df = user_rdd.map(str => {
      val splits = str.split("\\t")
      //封装样例类
      UserVisitAction(
        splits(0),
        splits(1).toLong,
        splits(2),
        splits(3).toLong,
        splits(4),
        splits(5),
        splits(6).toLong,
        splits(7).toLong,
        splits(8),
        splits(9),
        splits(10),
        splits(11),
        splits(12).toLong)
    }).toDF()

    //用DF / DS 的方式，更容易对数据进行处理
    user_df.show(10)
    println(user_df.schema)

    //方法1
//    val top10 = getTop10(user_df)
//    val top10_2  = getTop102(ssc,user_action)


    JumpRate(ssc,user_action,"1,2,3,4,5")

  }


  //DF : 根据点击次数分组
  def getTop10(df:DataFrame)={
    import org.apache.spark.sql.functions._

    val df_agg = df.groupBy("click_category_id").agg(count("click_category_id") as "count")
    val ret_ds = df_agg
      .withColumn("ROW", row_number().over(Window.orderBy(col("count").desc))).filter(col("ROW") <=10)

    ret_ds.show(false)
    ret_ds
  }



  //1.0 对rdd求top10
  def getTop102(sc:SparkContext, rdd:RDD[UserVisitAction])={
    //新建累加器
     val acc: CategoryAcc = new CategoryAcc
     sc.register(acc)
     rdd.collect().foreach(
        x=>acc.add(x) //将每一个rdd放到累加器
    )

    val actionMap: Map[String, Map[(String, String), Long]] = acc.acc_map.groupBy(_._1._1)//序号进行分组
    //封装样例类
    val action_Iter  = actionMap.map {
        case (cid, map) => {
          CategoryCountInfo(
            cid,
            map.getOrElse((cid, "click"), 0L),
            map.getOrElse((cid, "order"), 0L),
            map.getOrElse((cid, "pay"), 0L))
        }}

    //迭代器需要多次使用
    val action_list = action_Iter.toList
    //这里的排序
    //倒序排列 Ordering.Long.reverse, Ordering.Long.reverse, Ordering.Long.reverse
    // 怎么排序？？？
    val ret_rdd = action_list.sortBy(info => (info.clickCount,info.orderCount,info.payCount))(Ordering.Tuple3(Ordering.Long.reverse,Ordering.Long.reverse,Ordering.Long.reverse))
//      .take(3)

    ret_rdd.foreach(println(_))
    ret_rdd
  }


  //2.0 跳转率
  def JumpRate(sc:SparkContext,rdd:RDD[UserVisitAction],pages:String)= {

    val page = pages.split(",")
    val pre_page = page.slice(0, page.length - 1)
    val post_page = page.slice(1, page.length)

    val page_to_page = pre_page.zip(post_page).map { case (k, v) => {
      k + "->" + v
    }
    }


    // 前page 的值
    val page_count = rdd.filter(x => page.contains(x.page_id.toString))
      .map(x => (x.page_id, 1)).countByKey()

    page_count.foreach(println)

//    (4,3602)
//    (2,3559)
//    (1,3640)
//    (3,3672)


    val test = rdd.groupBy(_.session_id)
      //flatMap用法？ 偏函数
      .flatMap { case (_, actionIt) => {
      val list = actionIt.toList.sortBy(_.action_time)
      //.map(_.page_id)
      val page_1 = list.slice(0, list.length - 1)
      val page_2 = list.slice(1, list.length)
      val page_to_page2 = page_1.zip(page_2).map {
        case (page_1, page_2) => {
          page_1.page_id + "->" + page_2.page_id
        }
      }
      page_to_page2.map(x => (x, 1)) //过滤得到需要的 // .filter(x=>x.contains(page_to_page))
    }
    }.countByKey


    test.foreach(println)

    test.map {
      case (a, b) => {
        val page_to_page3 = a.split("->")
         val rate = b /page_count.getOrElse(page_to_page3(0).toInt,Long.MaxValue)
        null
      }
    }
  }


  def test_fun(spark:SparkContext,rdd:RDD[UserVisitAction],getrdd:List[CategoryCountInfo])={

   val filter_rdd =  rdd.filter(row =>
      getrdd.map(_.categoryId).contains(row.click_category_id)
    )

    val new_rdd = filter_rdd.map(x=>((x.click_category_id,x.session_id),1))
    val ten = new_rdd.reduceByKey(_+_).map{  //RDD[(Long, Iterable[(String, Int)])]
      case((x,y),z) => (x,(y,z))}.groupByKey()

    //groupBykey在使用中，会将数据结构转换为 Iterable的形式
    val ret_rdd = ten.map {
     //命名对结果有一定的影响的
      case (id,sideCount) => (id,sideCount.toList.sortBy(-_._2).take(10))
    }

    ret_rdd.collect.foreach(println)

  }
















  //传入参数： 样例类，结果
  //实现不同类型的--先理解数据
  class CategoryAcc extends AccumulatorV2[UserVisitAction, Map[(String, String), Long]] {
    //acc的map
    var acc_map = Map[(String,String),Long]()
    //true . false
    override def isZero: Boolean = acc_map.isEmpty  //map是否为空

    //复制一个map
    override def copy(): AccumulatorV2[UserVisitAction, Map[(String, String), Long]] = {
      val acc = new CategoryAcc
      acc.acc_map ++= acc_map  //++= 2个map的形式
      acc
    }

    override def reset(): Unit = acc_map = Map[(String,String),Long]()

    //add啥, 对进来的rdd数据进行处理
    override def add(v: UserVisitAction): Unit = {
      if(v.click_category_id != -1){
        acc_map += (v.click_category_id.toString,"click")->(acc_map.getOrElse((v.click_category_id.toString,"click"),0L)+1L)
      }else if(v.order_category_ids != "null"){
        val order_split = v.order_category_ids.split(",")
        order_split.foreach(x=> {
          acc_map += (x, "order") -> (acc_map.getOrElse((x, "order"), 0L)+ 1L)
        })
      }else if(v.pay_category_ids != "null"){
        val payIds: Array[String] = v.pay_category_ids.split(",")
        payIds.foreach(id => {
          acc_map += (id, "pay") -> (acc_map.getOrElse((id, "pay"), 0L) + 1L)
        })
      }
    }

    //merge啥
    override def merge(other: AccumulatorV2[UserVisitAction, Map[(String, String), Long]]): Unit = {
        val other_rdd = other.asInstanceOf[CategoryAcc]
        other_rdd.acc_map.foreach{  //foreach 与 map 的用法
          case ( id , actionCount)=>{
           this.acc_map += id -> (this.acc_map.getOrElse(id,0L) + actionCount) //把count加进去
      }}
    }

    //value
    override def value: Map[(String, String), Long] = acc_map
  }

}
