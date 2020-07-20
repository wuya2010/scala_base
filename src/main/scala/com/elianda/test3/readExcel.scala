package com.elianda.test3
import com.elianda.test3.COMMON_VAR_CUST_VIEW
import org.apache.poi.xssf.usermodel.XSSFWorkbook
/**
  * @author kylinWang
  * @data 2018/12/21 17:39
  *
  */
object readExcel {
  //
 val obj  =   new Object with COMMON_VAR_CUST_VIEW
  //标准路径
  val ddlPath = "config/DDL"

  def main(args: Array[String]): Unit = {
    //java对象 :  a new SpreadsheetML workbook.
   val write_sxssf = new XSSFWorkbook()
    write_sxssf.createSheet("first_sheet")
    val content = write_sxssf.createSheet("目录")

    ReadExcle2Excel(write_sxssf,"")

  }

  //传入2个参数 (xssfworkbook)
  def ReadExcle2Excel(write_sxssf: XSSFWorkbook, str: String): Unit = {



  }


}
