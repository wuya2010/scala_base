package com.elianda.test4

import java.io.{File, FileInputStream}

import com.alibaba.fastjson.JSON
import com.elianda.test3.BASE_CONFIG
import org.apache.commons.io.IOUtils
import org.apache.xmlbeans.impl.common.IOUtil

/**
  * @author kylinWang
  * @data 2018/12/26 9:34
  *
  */
object readJson {

  def main(args: Array[String]): Unit = {

        getJson

  }


  //将json文件放入样例类中
  def getJson={

    val CONF_FILE_NAME = "config/JSON/base4.json"
    //从文件路径获取流,从流获取数据
    val is = new FileInputStream(new File(CONF_FILE_NAME))
    val getIS = IOUtils.toString(is,"utf-8")
    //getClass与ClassOf
    val baseconfigs = JSON.parseArray(getIS,classOf[BASE_CONFIG])

  }

}
