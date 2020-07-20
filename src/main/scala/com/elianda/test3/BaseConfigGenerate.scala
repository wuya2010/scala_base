package com.elianda.test3

import java.io.{File, FileInputStream, PrintWriter}
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFWorkbook}
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * @author kylinWang
  * @data 2018/12/23 14:28
  *
  */


object BaseConfigGenerate {


  def main(args: Array[String]): Unit = {

    //通过args传递路径信息
    val path = args match{
      case a if a.length > 0 =>a(0)
      case _ => "mb_cv.xlsx"
    }
    //根据路径生成新的json
    val result = generateConfigByExcel(path)

    //写入到指定路径
    //java.io.PrintWriter是java中很常见的一个类，该类可用来创建一个文件并向文本文件写入数据
    val writer= new PrintWriter(new File("src/test/resources/base4.json"))

    //写入string的数据，在指定路径文件中写入json
    writer.write(result.toString)
    writer.close()
  }


  //1. runtoJson
   def runtoJson={}

  //2. getDefaultIfEmpty： 用模式匹配分配不同的情况
  def  getDefaultIfEmpty(value:XSSFCell,default:String)={
/*      //判断
      if(value != null) value.toString else default*/
    //用模式匹配
    // 用Option进行判断
    Option(value) match{
      case None => default
      case Some(value) => value.toString
      case _ => default
    }

  }



  //3. generateConfigByExcel ； 根据路径信息获取配置信息
  //具体生成json的逻辑
  def generateConfigByExcel(path: String) = {
      //根据路径获取excel的东西
    //通过将整个流缓冲到内存中，构造一个XSSFWorkbook对象
    val hssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(path)))
    //获取一共又多少页
    val sheetNum = hssfWorkbook.getNumberOfSheets
    println(sheetNum) //3页
    //从第二页开始做循环
    val firstSheet =  2
    var allConfig = ArrayBuffer[BASE_CONFIG]()

    //2. 做一个循环，逐页读取  测试：核心客户已完成；基础测试一个文档（全量数据
    // 手机银行：  +  权限 + 个金  ： 下午3点半
    //对每一页进行一个遍历 ： 从第2页开始加载
     for(i <- firstSheet until sheetNum){
       //首先做一个判断
         val sheet =  hssfWorkbook.getSheetAt(i)
         //获取最后的一行
         val all_row = sheet.getLastRowNum

         //获取具体参数: 第1行的，第0个单元格
         val tb_name = getDefaultIfEmpty(sheet.getRow(1).getCell(0),"")
         val filter = getDefaultIfEmpty(sheet.getRow(1).getCell(1),"")
         //用Option
         val file_type = Option(sheet.getRow(1).getCell(5)).getOrElse("orc").toString
         val file_sep = getDefaultIfEmpty(sheet.getRow(1).getCell(6),"")
         val file_path = getDefaultIfEmpty(sheet.getRow(1).getCell(7),"")

       /*  //对file_path进行替换，将名字转换为大写
          if(AppContext.ddl_relm_config.contains(tb_name)){
            //将路径替换为ddl_relm_config中对应的ddl,替为空
              file_path.replace(tb_name,AppContext.ddl_relm_config.get(tb_name).replace(".ddl","")).toUpperCase
          }*/

         val header = getDefaultIfEmpty(sheet.getRow(1).getCell(8),"false").toBoolean
         //类型推断
         val interSchema = getDefaultIfEmpty(sheet.getRow(1).getCell(9),"false").toBoolean


         val cache_enable = getDefaultIfEmpty(sheet.getRow(1).getCell(10),"false").toBoolean
         val cache_level = getDefaultIfEmpty(sheet.getRow(1).getCell(11),"1").toDouble.toInt

         val repartition_enable = getDefaultIfEmpty(sheet.getRow(1).getCell(12),"false").toBoolean
         val repartition_num = 1//getDefaultIfEmpty(sheet.getRow(1).getCell(13),"1").toDouble.toInt
         val repartition_cols = getDefaultIfEmpty(sheet.getRow(1).getCell(14),"")

          var options = getDefaultIfEmpty(sheet.getRow(1).getCell(15), "")

/*         //将数据封装到样例类
         case class Repartiton(Enable:Boolean,numPartition:Int,cols:String)
         Repartiton(repartition_enable.toBoolean,repartition_num,repartition_cols)*/


         //把全量增量整清楚,读取每行的数据，获取字段
         //初始容量16个字符 ； 2个容器
         val schema = new StringBuffer()
         var schemaMap =  Map.empty[String,String]

         // var Cols_all = mutable.ArrayBuffer[Cols]()  ：  定义什么样的变量
          var Cols_all = Array[Cols]()

         //获取原始字段及规格名字,从第1行开始读;redis的集群模式，建立与不同的数据库的连接
         //3,4 : 需要字段 + 字段别名    17，18，19字段， 类型， 名字; for
        for(j<- 1 to all_row){
            val schema1 = sheet.getRow(j).getCell(17).getStringCellValue.trim.toUpperCase.replace("-","_")

            val dttype = sheet.getRow(j).getCell(18).getStringCellValue.trim.toUpperCase match{
              //scala中字段的类型
              case "bigint"=>"LONG"
              case "string"=>"STRING"
              case "double" => "DOUBLE"
              case a if (a.contains("decimal"))=>"DECIMAL"
              case "number" => "LONG"
              case "varchar2" => "STRING"
              case "char" => "STRING"
              case "varchar" => "STRING"
              case "long" => "LONG"
              case "timestamp" => "TIMESTAMP"
              case _ => "STRING"
            }

          val content = getDefaultIfEmpty(sheet.getRow(j).getCell(19),"").trim.replace("-", "_")

          //如果字段不为空，放入容器中 ； scala中的泛型
          if(!schema1.trim.isEmpty){
            //放入map中  字段 + 名字
            //map获取名字对应关系
             schemaMap += schema1->content
            //如果schema非空
            if (!schema.toString().equals("")) {
              schema.append(",")
            }
            //拼接,二进制文件类型
            schema.append(schema1.trim.replace("-","_"))
            schema.append(" " + dttype.trim)
          }



          //已经获取了excel中源字段
          //可变的buffer； 将文字信心给ddl
          val excessFields = mutable.ArrayBuffer[String]() //增加字段
          val repeatFields = mutable.ArrayBuffer[String]() //重复字段


          //造一个容器,样例类的容器 Cols是样例类

          //实现一个什么  获取需求的字段，并给出别名
          for(i <- 1 to all_row) {
            try {
              val requireField = sheet.getRow(i).getCell(3).toString.trim.toUpperCase//获取第3列的数据
              val aliasField = sheet.getRow(i).getCell(4).toString.trim.toUpperCase//获取第4列的数据
              //封装到样例类中; 需要考虑多一点
              //如果第一行为空，报错
              if (i == 1 && requireField.equals("")) {
                println("配置文件有问题，不能为空")
              }
              //多个检验的情况,重复字段提出
              //val t = Cols_all.map(field => field.name).toSet
              if (Cols_all.map(field => field.name).toSet.contains(requireField)) {
                repeatFields.append(requireField)
              }
              //要有校验，与map进行比对，报错
              if (schemaMap.contains(requireField) && !requireField.equals("")) {
                excessFields.append(requireField)
              }
              //别名，2个都不为空，则写入
              if (!requireField.equals("") && !aliasField.equals("")) {
                //样例类放入容器中; 集合的基本操作
                Cols_all=Cols_all.:+(Cols(requireField, aliasField, schemaMap.getOrElse(requireField, "")))
              }
            } catch {
              //这里给什么信息
              case e: NullPointerException => println("空指针异常")
            }
          }

        //再做一个判断
 /*         if(repeatFields.size>0){
            throw new RuntimeException(s"重复字段${repeatFields.mkString(",")}")
          }
          //多出的字段
          if(excessFields.size>0) {
            throw new RuntimeException(s"多余字段${excessFields.mkString(",")}")
          }*/



  /*        println(tb_name,file_type,file_sep,file_path,header,interSchema,options,url,jdbc_tb_name,jdbc_username,
            jdbc_password,schema.toString,filter,null,CACHE(cache_enable,cache_level,tb_name),REPARTION(repartition_enable,repartition_num,repartition_cols))
*/

       }

       //测试
       // println(schemaMap)
       //   Cols_all.foreach(println(_))

       val url = ""
       val jdbc_tb_name=""
       val jdbc_username = "root"
       val jdbc_password = "123456"

       //将获得所有数据写入样例类中
         allConfig = allConfig :+ BASE_CONFIG(tb_name,file_type,file_sep,file_path,header,interSchema,options,url,jdbc_tb_name,jdbc_username,
         jdbc_password,schema.toString,filter,Cols_all,CACHE(cache_enable,cache_level,tb_name),REPARTION(repartition_enable,repartition_num,repartition_cols))

       println(JsonUtil.toJson(allConfig))

     }

    //生成json
    JsonUtil.toJson(allConfig)
  }
}
