package com.base.varity.traitdemo

/*

trait: 就是接口
 */
object TraitDemo {
    def main(args: Array[String]): Unit = {
        val usb:Console = new HaweiUsb

        //重写了print的方法
        usb.print()
    }
}

trait Usb{
    def insert(): Unit
    def work(): Unit
    def unInsert(): Unit
    
}

trait Console{
    def print(): Unit
}


//多接口，多混入 with
class HaweiUsb extends Usb with Console{
    override def insert(): Unit = {
        println("华为的usb插入")
    }
    
    override def work(): Unit = {
        println("huawei usb work")
    }
    
    override def unInsert(): Unit = {
        println("drow huaw usb")
    }
    
    override def print(): Unit = {
        insert()
        work()
        unInsert()
    }
}



/*
1.7 之前: 常量和抽象方法的集合
1.8 : default 方法

---

trait  特质

多混入

 */