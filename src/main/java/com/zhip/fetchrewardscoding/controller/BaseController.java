package com.zhip.fetchrewardscoding.controller;

import com.zhip.fetchrewardscoding.model.ResultData;


/**
 * @author Zhipeng Yin
 */
public class BaseController {
    private static final int SUCCESS_CODE = 200;



    public static ResultData success(Object data) {
        return getResult(SUCCESS_CODE, "", data);
    }

    public static ResultData success() {
        return getResult(SUCCESS_CODE, "", "");
    }

    public static ResultData success(String message){
        return  getResult(SUCCESS_CODE,message,"");
    }

    public static ResultData error(int code, String message) {
        return getResult(code, message, "");
    }

    public static ResultData getResult(int code, String message, Object data) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
