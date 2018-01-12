/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.service.ICrawlTaobaoService;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

/**
 * 根据关键词抓取淘宝宝贝
 *
 * @author SayMing
 */
@Service("searchProductByKeywordsTaobaoService")
public class SearchProductByKeywordsTaobaoServiceImpl implements ICrawlTaobaoService<List> {

    private OkHttpClient httpClient = new OkHttpClient();

    /**
     *
     * @param result
     * @return 返回的是宝贝Ids
     */
    @Override
    public List crawl(List result) {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost:8084/TaobaoTools/taobaoProductIds.json")
                .newBuilder()
                //.addQueryParameter("username", "user")
                //.addQueryParameter("password", "user123")
                .build();
        Request request = new Request.Builder().url(httpUrl.toString()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String bodyStr = response.body().string();
                System.out.println(bodyStr);
                //从bodyStr中找到itemList js 代码。
                
                JSONObject json = JSONObject.parseObject(bodyStr);
                if (json.containsKey("mods")) {
                    JSONObject modsJson = json.getJSONObject("mods");
                    if (modsJson.containsKey("itemlist")) {
                        JSONArray auctions = modsJson.getJSONObject("itemlist").getJSONObject("data").getJSONArray("auctions");
                        for (int i = 0; i < auctions.size(); i++) {
                            JSONObject item = auctions.getJSONObject(i);
                            result.add(item.getString("nid"));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
