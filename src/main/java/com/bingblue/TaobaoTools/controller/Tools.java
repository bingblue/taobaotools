/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author SayMing
 */
public class Tools {

    public static JSONObject success(JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Result", Status.SUCCESS);
        jsonObject.put("Data", data);
        return jsonObject;
    }

    public static JSONObject error(JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Result", Status.ERROR);
        jsonObject.put("Data", data);
        return jsonObject;
    }

    public static JSONObject error(String errorMsg) {

        JSONObject errorMsgObject = new JSONObject();
        errorMsgObject.put("Msg", errorMsg);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Result", Status.ERROR);
        jsonObject.put("Data", errorMsgObject);
        return jsonObject;
    }

    public static UserAgent checkUserAgent(String userAgent){
        if(userAgent == null || userAgent.isEmpty()){
            return UserAgent.OTHER;
        }
        if(userAgent.contains("MicroMessenger")){
            return UserAgent.WEIXIN;
        }else if(userAgent.contains("Alipay")){
            return UserAgent.ALIPAY;
        }else{
            return UserAgent.OTHER;
        }
    }
    
    public enum UserAgent {
        WEIXIN, ALIPAY, IE, CHROME, FIREFOX, OTHER
    }
}
