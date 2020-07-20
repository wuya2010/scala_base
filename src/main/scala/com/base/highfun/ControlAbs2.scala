package com.base.highfun

/**
  * Author kylin
  * Date 2018-09-04 16:15
  */
object ControlAbs2 {
    def main(args: Array[String]): Unit = {
        runInThread{
            //...
            println(Thread.currentThread().getName)
        }
        
        runInThread{
            //....
        }
    }

    //名调用
    def runInThread(f: => Unit) ={
        new Thread(){
            override def run(): Unit = f
        }.start()
    }
}


/*
写一个函数, 可以运行一段代码在一个子线程中


 */