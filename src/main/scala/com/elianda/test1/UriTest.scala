package com.elianda.test1

import java.net.URLDecoder

import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.{DefaultFullHttpResponse, FullHttpRequest, HttpResponseStatus, HttpVersion}
import io.netty.util.AsciiString
import jdk.nashorn.internal.ir.RuntimeNode.Request

object UriTest {
  def main(args: Array[String]): Unit = {

    val para = parseGetParam("/https://www.baidu.com/s?ie=utf-8&f=8&55")

    val ie = para.getOrElse("zz","")
    println(ie)

    val response = defaultResponse("{\"code\":500,\"msg\":\" 日期参数,模型参数为空\"}")

    println(response)
  }


  def parseGetParam(uri: String)= {
    var map = Map.empty[String, String]
    val array = URLDecoder.decode(uri, "utf-8").split("\\?")
    println(array.mkString(","))
    if(array.length>1) {
      val params = array.apply(1).split("&").map(_.trim)
      println(params.mkString(","))
      if(params.length>1){
        params.foreach(str =>{
          val strAll = str.split("=")
          if(strAll.length >1){
            map += strAll.apply(0)->strAll.apply(1)
          }else if(strAll.length >0){
            map += strAll.apply(0)->""
          }
        })
        println(map)
      }
    }
    map
  }


//测试方法2
  def defaultResponse(respContent: String):DefaultFullHttpResponse={
    val response = new DefaultFullHttpResponse(
      HttpVersion.HTTP_1_1,
      HttpResponseStatus.OK,
      Unpooled.wrappedBuffer(respContent.getBytes())
    )

    val ContentType = AsciiString.cached("Content-Type")
    val ContentLength = AsciiString.cached("Content-Length")

    response.headers().set(ContentType, "application/json")
    response.headers().setInt(ContentLength, response.content().readableBytes())
    response
  }

}