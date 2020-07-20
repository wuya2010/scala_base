package com.elianda.test1

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import org.slf4j.LoggerFactory

object ThreadTest {
  def main(args: Array[String]): Unit = {

    val logger = LoggerFactory.getLogger(this.getClass)
    val model_name = "xxx"

     val threadpool = new ThreadPoolExecutor(
      1, // core pool size
      1, // max pool size
      500, // keep alive time
      TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue[Runnable]()
    )

    threadpool.execute(new Runnable {
      override def run(): Unit = {
        try{
          if(true){
            print("success")
            //logger写在日志中
            logger.info(s"加载模型${model_name}外部DDL")
          }
        }catch{
          case ex:Exception=>{
            ex.printStackTrace()
          }
        }
      }
    })


  }

}
