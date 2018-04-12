/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.TaobaoShopProductDao;
import com.bingblue.TaobaoTools.pojo.TaobaoShop;
import com.bingblue.TaobaoTools.pojo.TaobaoShopProduct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author SayMing
 */
public class TaobaoShopSpiderService {

    private static Logger logger = Logger.getLogger(TaobaoShopSpiderService.class);

    @Resource(name = "taobaoShopSpiderLocalCookieStore")
    private LocalCookieStore cookieStore;

    @Resource
    private TaobaoShopProductDao taobaoShopProductDao;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.add(url, cookies);
                    logger.info("Response has cookies ===> " + (cookies != null) + " Response url :" + url.toString());
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = (List<Cookie>) cookieStore.get(url);
                    logger.info("Request has cookies ===> " + (cookies != null) + " Request url :" + url.toString());
                    return cookies;
                }
            })
            .build();

    /**
     * 根据url获取页面内容
     *
     * @param url 指定访问的url
     * @return 页面内容
     */
    public String getPageBody(String url) {

        HttpUrl httpUrl = HttpUrl.parse(url)
                .newBuilder()
                .build();

        logger.info("url is ===>" + url);
        Request request = new Request.Builder().url(httpUrl)
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
        }

        return bodyStr;
    }

    public boolean search(TaobaoShop taobaoShop, String shopUrl) {
        String shopPageBody = getPageBody(shopUrl);
        if (shopPageBody != null) {
            String asynSearchUrl = findAsynSearchUrl(shopPageBody);
            if (asynSearchUrl != null) {
                String shopSearchProductPageBody = getPageBody(asynSearchUrl);
                List<TaobaoShopProduct> products = findProduct(shopSearchProductPageBody);
                //添加到数据库中
                products.forEach((product) -> {
                    //计算店铺单个商品总销售额，大致
                    product.setTotalSale(product.getPrice() * product.getTotalSaleQuantity());
                    taobaoShopProductDao.insert(product);

                    //累计店铺总销量，总销售额
                    taobaoShop.setTotalSale(taobaoShop.getTotalSale() + product.getTotalSale());
                    taobaoShop.setTotalSaleQuantity(taobaoShop.getTotalSaleQuantity() + product.getTotalSaleQuantity());
                });

                String nextAsynSearchUrl = findNextUrl(shopSearchProductPageBody);
                if (nextAsynSearchUrl != null) {//继续查找下一页
                    search(taobaoShop, nextAsynSearchUrl);
                }
            }
        }
        return false;
    }

    /**
     * 从页面中获取真正的搜索商品链接
     *  
     * @param htmlBody 页面内容
     * @return 搜索商品链接不含协议头 //ldmhw.tmall.com/i/asynSearch.htm?mid=w-14640068132-0&wid=14640068132&path=/search.htm&search=y&spm=a1z10.3-b-s.w4011-14640068132.86.70041792Pmgjqp&tbpm=3&pageNo=1&tsearch=y
     */
    public String findAsynSearchUrl(String htmlBody) {
        try {
            Document htmlDocument = Jsoup.parse(htmlBody);
            Element shopSearchUrlEl = htmlDocument.getElementById("J_ShopSearchUrl");
            Element shopAsynSearchURLEl = htmlDocument.getElementById("J_ShopAsynSearchURL");
            if(shopSearchUrlEl != null && shopAsynSearchURLEl != null){
                return shopSearchUrlEl.val() + shopAsynSearchURLEl.val();
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        return null;
    }

    /**
     * 从页面内容中获取商品集合
     *
     * @param htmlBody 页面内容
     * @return 商品集合
     */
    public List<TaobaoShopProduct> findProduct(String htmlBody) {
        List<TaobaoShopProduct> productList = new ArrayList<>();
        try {
            Document htmlDocument = Jsoup.parse(htmlBody);
            Element shopSearchUrlEl = htmlDocument.getElementById("J_ShopSearchUrl");
            Element shopAsynSearchURLEl = htmlDocument.getElementById("J_ShopAsynSearchURL");
            if(shopSearchUrlEl != null && shopAsynSearchURLEl != null){
                //return shopSearchUrlEl.val() + shopAsynSearchURLEl.val();
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        return productList;
    }

    /**
     * 是否存在下一页
     *
     * @param htmlBody 页面内容
     * @return 下一页地址，null-不存在
     */
    public String findNextUrl(String htmlBody) {
        return null;
    }

}
