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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 根据关键词抓取淘宝宝贝
 *
 * @author SayMing
 */
@Service("searchProductByKeywordsTaobaoService")
public class SearchProductByKeywordsTaobaoServiceImpl implements ICrawlTaobaoService<List> {

    private static Logger logger = Logger.getLogger(SearchProductByKeywordsTaobaoServiceImpl.class);
    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .build();
    private static final String BASE_TAOBAO_URL = "https://s.taobao.com/search";
    private static final String FIND_PRODUCT_REG_EX = "(?<=g_page_config = )[\\s\\S]*?(\\}(?=;))";

    /**
     *
     * @param result
     * @param params
     * @return 返回的是宝贝Ids
     */
    @Override
    public List crawl(List result, Map params) {

        //https://s.taobao.com/search?q=%E6%B4%97%E5%8F%91%E6%B0%B4&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20180113&ie=utf8
        String keywords = (String) params.get("keywords");

        HttpUrl httpUrl = HttpUrl.parse(BASE_TAOBAO_URL)
                .newBuilder()
                .addQueryParameter("q", keywords)
                .build();
        Request request = new Request.Builder().url(httpUrl.toString())
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36")
                .build();
        logger.info("keywords is ===>" + keywords);
        try (Response response = httpClient.newCall(request).execute()) {
            logger.info("response status ===>" + response.isSuccessful());
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                logger.info("responseBody is null ===>" + responseBody == null);
                if (responseBody != null) {
                    String bodyStr = responseBody.string();
                    logger.info("bodyStr length ===>" + bodyStr.length());
                    //从bodyStr中找到itemList js 代码。
                    Pattern pattern = Pattern.compile(FIND_PRODUCT_REG_EX);
                    Matcher matcher = pattern.matcher(bodyStr);
                    boolean find = matcher.find();
                    logger.info("find ===>" + find);
                    if (find) {
                        String findJSON = matcher.group();
                        logger.info("findJSON ===>" + findJSON);
                        JSONObject json = JSONObject.parseObject(findJSON);
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
                }
                logger.info("result size ===>" + result.size());
                logger.info("result list ===>" + result.toString());
            }
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return result;
    }

}
