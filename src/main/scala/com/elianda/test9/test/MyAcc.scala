package com.elianda.test9.test

import org.apache.spark.util.AccumulatorV2

/**
  * @author kylinWang
  * @data 2018/3/6 20:08
  *   自定义累加器
  */
class MyAcc extends  AccumulatorV2[Long,Long]{

  var sum = 0L

  //判读是否是0
  //  Returns if this accumulator is zero value or not. e.g. for a counter accumulator, 0 is zero
  //   value; for a list accumulator, Nil is zero value.
  override def isZero: Boolean = sum == 0

  //复制每一个累加器
  //Creates a new copy of this accumulator.
  override def copy(): AccumulatorV2[Long, Long] = //{???}
  {
    val acc = new MyAcc
    acc.sum = sum
    acc
  }

//  Resets this accumulator, which is zero value. i.e. call `isZero` must
//  return true.
  override def reset(): Unit = sum = 0


//  Takes the inputs and accumulates.
  override def add(v: Long): Unit = sum += v


//  Merges another same-type accumulator into this one and update its state, i.e. this should be
//    merge-in-place.
  override def merge(other: AccumulatorV2[Long, Long]): Unit =
  {
    other match {
      case t:MyAcc => this.sum += t.sum
      case _ => throw new IllegalArgumentException
    }
  }


  //Defines the current value of this accumulator
  override def value: Long = this.sum
}
