package com.base.arr

/**
  * Author kylin
  * Date 2018-09-07 10:27
  */
object ArrayDemo2 {
    def main(args: Array[String]): Unit = {

        //ofDim
        val array: Array[Array[Int]] = Array.ofDim[Int](2,3)
        for (arr <- array) {
            for (elem <- arr) {
                println(elem)
            }
        }
    }
}
