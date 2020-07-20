package com.scala.collect

import org.apache.spark.metrics.source
import spire.macros.Auto.scala

import _root_.scala.io.Source
;

/**
  * @author kylinWang
  * @data 2018/12/21 0:37
  *
  */
        object HighFunc {

          def main(args: Array[String]): Unit = {

            test4


          }

          //读取路径中的文件
          def test0: Unit ={

              val path = "E:\\ChinaBank\\first_project - test\\src\\main\\resources\\108fields.txt"
              //获取每一行
              val line = Source.fromFile(path).getLines()

              line.foreach(data=>println(data))
          }


        //改变数据类型
        def test2: Unit ={
          val list = List(12,2,3,8,15,14)
          //过滤出偶数的部分
          val list2 = list.filter(data=>data%2!=1)
          //对符合要求的每一个数进行map处理
          val list3 = list.filter(_ %2!=1).map(x=>x*x)
          println(list2)
          println(list3)
        }


        //通过instanceof进行设置
        def test3 {
          val list1 = List(null, 30, 50, 70, 60, 10, 20, true, "a")
          //过滤出int， 但得到的是Any
          val list2 = list1.filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int]).map(_+"1")


          println(list2)
        }

        //list的组合
        def test4: Unit ={
          //返回值的类型，String
          val arr = Array("hello world", "atguigu hello", "hello hello hello")
         //不改变原来数据的结构
          val arr1 = arr.flatMap(_.split(" "))
          //[Ljava.lang.String;@2833cc44 : 输出的并不是想要的结果
          println(arr1)
          //对每一个单词分开： h_e_l_l_o
          arr1.foreach(data=>println(data.mkString("_")))
          //每一个打印： hello
          arr1.foreach(println)

          println("================")
          //map改变了结构
          val arr2 = arr.map(_.split(" "))
          println(arr2)
          //hello_world
          arr2.foreach(data=>print(data.mkString("_")+","))

          arr2.foreach(data=>{
            // hello
            data.foreach(println)
          })
        }

        //arr测试
        def test5: Unit ={
          val list1 = List(30, 50, 70, 60, 10, 20)
          //flatMap的用法 =》 不改变结构List(30, 900, 50, 2500, 70, 4900, 60, 3600, 10, 100, 20, 400)
          val list2 =  list1.flatMap(x=>Array(x,x*x))
          println(list2)

          println("========================")

          val s = "abc"
          val s2 = s.flatMap(x=>Array(x,x.toUpper))
          //aAbBcC
          println(s2)
        }
   }
