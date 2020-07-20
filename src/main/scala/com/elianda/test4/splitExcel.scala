package com.elianda.test4

import java.io.{File, FileInputStream, FileOutputStream, OutputStreamWriter}

import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}


/**
  * @author kylinWang
  * @data 2018/12/31 16:00
  *
  */
        object splitExcel {

          def main(args: Array[String]): Unit = {

            splitExcelTest
          }

          //分隔excel
          def splitExcelTest: Unit ={
            val path = "mb_cv.xlsx"
            val workbook = new XSSFWorkbook(new FileInputStream(new File(path)))
            val numSheets = workbook.getNumberOfSheets

            //从每一页开始读
            for(i <- 2 to numSheets){
              val perSheet: XSSFWorkbook = new XSSFWorkbook()
              val perName: String = workbook.getSheetName(i)
              val copySheet: XSSFSheet = workbook.cloneSheet(i)
              //写到一个新的excel中,根据已知的名字
              val newSheet: XSSFSheet = perSheet.createSheet(perName)
              //写入到新的excel
              ExcelOperationUtil.copySheet(copySheet,newSheet)
              //用流的方式写入新的文件中
              val fos: FileOutputStream = new FileOutputStream(new File("NEW_EXCEL/"+perName+".xlsx"),false)
             val writer: OutputStreamWriter = new OutputStreamWriter(fos,"utf-8")
              println(i)
              //将流数据写入
              perSheet.write(fos)
              fos.close()
              perSheet.close()
            }
          }

        }
