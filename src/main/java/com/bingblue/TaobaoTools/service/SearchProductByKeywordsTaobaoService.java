/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.pojo.ProductCatchList;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
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
@Service
public class SearchProductByKeywordsTaobaoService {

    private static Logger logger = Logger.getLogger(SearchProductByKeywordsTaobaoService.class);

    @Resource
    private TaobaoProductService taobaoProductService;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .build();
    private static final String BASE_TAOBAO_URL = "https://s.taobao.com/search?&imgfile=&js=1&stats_click=search_radio_all%3A1&ie=utf8";
    private static final String FIND_PRODUCT_REG_EX = "(?<=g_page_config = )[\\s\\S]*?(\\}(?=;))";

    /**
     * https://s.taobao.com/search?q=%E6%B4%97%E5%8F%91%E6%B0%B4&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20180113&ie=utf8
     * @param keywords 搜索关键字
     * @return 商品Ids
     */
    public List crawl(String keywords) {
        List result = new ArrayList();
        //1.查本地数据库
        String productIds = taobaoProductService.nowLocalProductCatchList(keywords);
        if (null == productIds || productIds.isEmpty()) {
            LocalDate now = LocalDate.now();
            DateTimeFormatter df = DateTimeFormatter.BASIC_ISO_DATE;
            try {
                String keywordsEncode = URLEncoder.encode(keywords, "utf-8");
                String initiative_id = "staobaoz_" + df.format(now);
                String url = BASE_TAOBAO_URL + "&q=" + keywordsEncode + "&initiative_id=" + initiative_id;

                String bodyStr = getTaobaoHtml(1, url);

                if (bodyStr != null) {
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
                                StringBuilder productIdsB = new StringBuilder();
                                for (int i = 0; i < auctions.size(); i++) {
                                    JSONObject item = auctions.getJSONObject(i);
                                    String nId = item.getString("nid");
                                    result.add(nId);
                                    productIdsB.append(",").append(nId);
                                }
                                //插入本地数据库
                                if(productIdsB.length() > 0){
                                    ProductCatchList productCatchList = new ProductCatchList();
                                    productCatchList.setHappendate(new Date());
                                    productCatchList.setKeywords(keywords);
                                    productCatchList.setProductids(productIdsB.substring(1));
                                    taobaoProductService.insertProductCatchList(productCatchList);
                                }
                            }
                        }
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                logger.info("keywords URLEncoder.encode error.");
                logger.error(ex);
            }
        } else {
            result.addAll(Arrays.asList(productIds.split(",")));
        }

        logger.info("result size ===>" + result.size());
        logger.info("result list ===>" + result.toString());

        return result;
    }

    private String getTaobaoHtml(int tryNum, String url) {
        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .build();

        logger.info("url is ===>" + url);
        Request request = new Request.Builder().url(httpUrl.toString())
                .addHeader("referer", url)
                .addHeader("upgrade-insecure-requests", "1")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36")
                .build();
        String bodyStr = null;
        try (Response response = httpClient.newCall(request).execute()) {
            logger.info("response status ===>" + response.isSuccessful());
            if (response.isSuccessful()) {
                try (ResponseBody responseBody = response.body()) {
                    logger.info("responseBody is null ===>" + (responseBody == null));
                    if (responseBody != null) {
                        bodyStr = responseBody.string();
                        logger.info("bodyStr length ===>" + bodyStr.length());
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

        if ((bodyStr == null || bodyStr.isEmpty()) && tryNum <= 5) {
            logger.info("try num ===>" + tryNum);
            tryNum++;
            bodyStr = getTaobaoHtml(tryNum, url);
        }
        return bodyStr;
    }

}
