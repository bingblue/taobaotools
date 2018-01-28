/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;

/**
 * {
 * "code":200, //200成功，其它code 是失败 "msg":"", //成功消息或失败消息，可以不传 "body": { "Tpwd":
 * "￥TestAADPOKFzTest￥", //淘口令 "Url": "123"//原url } }
 *
 * @author SayMing
 */
public class Tools {

    public static JSONObject success(JSONObject body) {
        return success(body, null);
    }

    public static JSONObject success(String msg) {
        return success(null, msg);
    }

    public static JSONObject success(JSONObject body, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", Status.SUCCESS.getStatus());
        jsonObject.put("msg", msg == null || msg.isEmpty() ? "成功。" : msg);
        if (body != null) {
            jsonObject.put("body", body);
        }
        return jsonObject;
    }

    public static JSONObject error(JSONObject body) {
        return error(body, null);
    }

    public static JSONObject error(String errorMsg) {
        return error(null, errorMsg);
    }

    public static JSONObject error(JSONObject body, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", Status.ERROR.getStatus());
        jsonObject.put("msg", msg == null || msg.isEmpty() ? "错误。" : msg);
        if (body != null) {
            jsonObject.put("body", body);
        }
        return jsonObject;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static UserAgent checkUserAgent(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return UserAgent.OTHER;
        }
        if (userAgent.contains("MicroMessenger")) {
            return UserAgent.WEIXIN;
        } else if (userAgent.contains("Alipay")) {
            return UserAgent.ALIPAY;
        } else {
            return UserAgent.OTHER;
        }
    }

    public enum UserAgent {
        WEIXIN, ALIPAY, IE, CHROME, FIREFOX, OTHER
    }
}
