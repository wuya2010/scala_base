package com.spark.test1

import java.io.{File, FileInputStream, FileOutputStream, OutputStreamWriter}

import com.elianda.test4.ExcelOperationUtil
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}

/**
  * @author kylinWang
  * @data 2018/6/1 19:08
  *
  */
object ColenExcel {

  def main(args: Array[String]): Unit = {

    val file_path= "E:\\ChinaBank\\first_project - test\\src\\main\\scala\\com\\spark\\test1\\FilePath\\按业务条线查询模型（整理）.xlsx"
    val path = "/"

    val workbook = new XSSFWorkbook(new FileInputStream(new File(file_path)))
    val sheets = workbook.getNumberOfSheets

    for(i<-0 until sheets){
          val work = new XSSFWorkbook()
          val sheet_name = workbook.getSheetName(i)
          val old_sheet = workbook.cloneSheet(i)
          val new_sheet = work.createSheet(sheet_name)

          ExcelOperationUtil.copySheet(old_sheet,new_sheet)
          //重新生成
         val fos =  new FileOutputStream(new File(file_path + sheet_name + ".xlsx"),true)
          val out = new OutputStreamWriter(fos,"utf-8")
//          work.write(fos)
          fos.close()
          work.close()
    }

    mergeExcel()

  }

  def mergeExcel(): Unit = {

    val filePath = new File("E:\\ChinaBank\\first_project - test\\src\\main\\scala\\com\\spark\\test1\\FilePath") //读取所有excel
    val distwb = new XSSFWorkbook() //新建目的excel
    //var num = 1;
    filePath.listFiles().foreach(file => {
      if (true) {
        //复制sheet页的第一个页面数据
        val srcWb = new XSSFWorkbook(new FileInputStream(file))
        val sheetName = srcWb.getSheetName(0)
        val xssfsheet: XSSFSheet = srcWb.cloneSheet(0)
        val distsheet = distwb.createSheet(file.getName.replaceAll(".xlsx", ""))
        ExcelOperationUtil.copySheet(xssfsheet, distsheet)
      }
    })

    val out = new FileOutputStream(new File("dist.xlsx"), false)
//    var a = new OutputStreamWriter(out, "utf-8")
    distwb.write(out)
    out.close()
    distwb.close()


  }

}
