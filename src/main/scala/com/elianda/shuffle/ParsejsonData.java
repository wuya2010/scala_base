package com.elianda.shuffle;

import com.alibaba.fastjson.JSONObject;

/**
 * @author kylinWang
 * @data 2018/1/16 10:48
 */
public class ParsejsonData {

    public static JSONObject getJsonData(String data){

        try {
            JSONObject jsonObject = JSONObject.parseObject(data);

            return jsonObject;
        } catch (Exception e) {
            return null;
        }


    }
}
