package com.base.highfun

/**
  * Author kylin
  * Date 2018-09-04 16:15
  */
object ControlAbs4 {
    def main(args: Array[String]): Unit = {
        f1(() => {
        
        })

         f2 {

        }


    }
    
    def f1(op: () => Unit) = {
        op
        op

    }
    
    def f2(op: => Unit) = {
        op
        op

    }
    
}

/*
myWhile


 */