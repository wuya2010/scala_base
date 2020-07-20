package com.elianda.test8

import org.apache.spark.sql.functions.udf

/**
  * @author kylinWang
  * @data 2018/5/21 20:41
  *
  */
object UserdefinedFunc {
  def main(args: Array[String]): Unit = {




    //自定义 UDF
    val typexx_udf = udf((uuid: String, tran_msg: String) => {
      if ("境内外币" == tran_msg && uuid != null && uuid.startsWith("61")) {
        "境内外币参与行"
      } else if (uuid != null && tran_msg != null && uuid.startsWith("81") && tran_msg.contains("收费")) {
        "大小额"
      } //(ctis.uuid NOT like '61%' and ctis.uuid NOT like '81%' AND ctis.uuid NOT like 'BC%' AND ctis.uuid NOT like 'VCP%'  AND ctis.uuid NOT like '480%' AND ctis.uuid NOT like '410%'  and tran_msg not like '%贷款本息%')
      else if (uuid == null
        || (!uuid.startsWith("61") && !uuid.startsWith("81")
        && !uuid.startsWith("BC") && !uuid.startsWith("VCP")
        && !uuid.startsWith("480") && !uuid.startsWith("410")
        && (tran_msg == null || !tran_msg.contains("贷款本息")))) {
        "行内调拨"
      }
      else
        ""
    })
  }

}
