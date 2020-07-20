package com.scala.flodLeftFun
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions._
/**
  * @author kylinWang
  * @data 2018/4/17 10:01
  *   对拉链的测试
  */
object AboutZip {

  def main(args: Array[String]): Unit = {

    val list0 = Seq("1","2")
    val list1 = Seq(Seq(col("a") and col("b")))
    val list2  = Seq(col("1"),col("2"))
    val list3 = Seq(Seq(col("a"),col("b")),Seq(col("1"),col("2")),Seq(col("I"),col("II")))

    val list_all = list1.zip(list2)
    //生成交叉的结构
    /*    val list4 = list3.foldLeft(Map[String,String]())((map,list)  =>{

    })*/

    //组合成and     x zip y map (m => m._1.and(m._2))
    // 初始值是x  ；  y是传入的集合 ； zip不能交叉，所以还是得用循环
    val list5 = list3.foldLeft(Seq(lit(true),lit(true))){(x,y)=>{
        //x是初始值， y是

        x.zip(y).map(m=>m._1 and m._2)
    }}

    //折叠是对集合中的每一个元素进行迭代处理
//    val list6 = for(i<-list3)

  var seq1  = Seq[Column]()

 for(i<-list3.apply(0);j<-list3.apply(1)){
//  for(i<-list1;j<-list2){
        val temp = Seq(i and j)
        seq1 = seq1 ++ temp
   }

/*    val s = list0.size
    val l = list0.length

    println(s +"-s," +l)*/


    println("=================")
    println(seq1) //List((a AND 1), (a AND 2), (b AND 1), (b AND 2))



   val t0 = combined2(list3)

    println("========ttttt=============")
    println(t0)
  }

  def combined2(conditionList: Seq[Seq[Column]]) = {
    val len = conditionList.length
    var getSeq  = Seq[Column]()
/*    val ret = len match {
      case 1 => getSeq ++ conditionList.apply(0)
      case 2 =>
      }}
      case _ => "传入参数数量过多..."
    }*/

    //根据不同的情况，分类进行处理
    if(len>0){
      if(len ==1){
        getSeq = conditionList.apply(0)
      }

    if(len ==2) {
      for (i <- conditionList.apply(0); j <- conditionList.apply(1)) {
        val temp = Seq(i and j)
        getSeq= getSeq ++ temp
      }
    }

      if (len == 3) {
        for (i <- conditionList.apply(0); j <- conditionList.apply(1); k <- conditionList.apply(2)) {
          val temp = Seq(i and j and k)
          getSeq= getSeq ++ temp
        }
      }

      if(len >3){
          println("传入参数超过3个....")
      }
    }

      getSeq
  }


}
