package com.base.pattern

/*
匹配对象
 */
object PatternObj3 {
  def main(args: Array[String]): Unit = {
    val r: Double = 9

    r match {
      case Square(a)=>println(a)
    }

  }
}


//对象 squere
object Square {

  //匹配的是 r ：将r传到参数中去
    def unapply(abc: Double) = if(abc >= 0) Some(math.sqrt(abc)) else None

}


/*

对象匹配:

对象提取器:
unappy的返回值必须是 Option 类型  强制进行判断
如果返回时Some表示匹配成功
如果返回的None表示匹配失败  Nothing*/
