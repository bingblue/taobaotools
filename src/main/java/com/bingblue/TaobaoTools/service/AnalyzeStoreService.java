/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.dao.AnalyzeStoreBillDao;
import com.bingblue.TaobaoTools.dao.AnalyzeStoreDetailDao;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreBill;
import com.bingblue.TaobaoTools.pojo.AnalyzeStoreDetail;
import com.bingblue.TaobaoTools.pojo.BillStatus;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 店铺分析服务 1.用户提交店铺URL 2.后台接收用户提交的店铺URL，记录订单信息。
 * 3.后台提交爬虫服务（JSON）店铺URL、用户ID、店铺分析订单ID。 4.爬虫服务爬取结果返回到后台（JSON）店铺分析ID，用户ID，结果数据。
 * 5.后台接收爬虫服务返回的结果，插入数据，修改订单状态。
 *
 * @author hong
 */
@Service
public class AnalyzeStoreService {

    private static Logger logger = Logger.getLogger(AnalyzeStoreService.class);

    private static final String SPIDER_ROOT = "";
    private static final String SPIDER_URL = SPIDER_ROOT + "";
    private boolean debug = true;

    @Resource
    private AnalyzeStoreBillDao analyzeStoreBillDao;

    @Resource
    private AnalyzeStoreDetailDao analyzeStoreDetailDao;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(12, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS).build();

    /**
     * 接收用户提交的订单
     *
     * @param catchUrl 需要分析的店铺url
     * @param memberId 会员Id
     * @return 订单Id
     */
    public Long receiveBill(String catchUrl, Long memberId) {
        AnalyzeStoreBill bill = new AnalyzeStoreBill();
        bill.setStatus(BillStatus.DISPOSE.getType());
        bill.setCatchUrl(catchUrl);
        bill.setHappenDate(new Date());
        bill.setMemberId(memberId);
        analyzeStoreBillDao.insert(bill);

        //后台提交爬虫服务（JSON）店铺URL、用户ID、店铺分析订单ID。
        if (!debug) {
            sendToSpiderServiceAsyn(catchUrl, memberId, bill.getId());
        }
        return bill.getId();
    }

    /**
     * 接收爬取到的店铺明细商品信息数据，并录入数据库。
     *
     * @param result 爬取结果
     */
    public void receiveResultBill(String result) {
        //↓↓↓↓↓↓↓↓↓↓↓↓↓↓Test↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        result = "{\n"
                + "	\"code\": \"200\",\n"
                + "	\"msg\": \"success\",\n"
                + "	\"body\": {\n"
                + "		\"billId\": 2,\n"
                + "		\"shopName\": \"店铺名称\",\n"
                + "		\"shopId\": 67694825,\n"
                + "		\"remark\": \"备注\",\n"
                + "		\"products\": [{\n"
                + "				\"name\": \"商品名称\",\n"
                + "				\"price\": 30.31,\n"
                + "				\"saleQuantity\": 121,\n"
                + "				\"id\": \"淘宝商品Id1\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"name\": \"商品名称\",\n"
                + "				\"price\": 30.32,\n"
                + "				\"saleQuantity\": 122,\n"
                + "				\"id\": \"淘宝商品Id2\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"name\": \"商品名称\",\n"
                + "				\"price\": 30.33,\n"
                + "				\"saleQuantity\": 123,\n"
                + "				\"id\": \"淘宝商品Id3\"\n"
                + "			}\n"
                + "		]\n"
                + "	}\n"
                + "}";
        //↑↑↑↑↑↑↑↑↑↑↑↑↑Test↑↑↑↑↑↑↑↑↑↑↑↑↑
        
        JSONObject resultJSON = JSONObject.parseObject(result);
        JSONObject body = resultJSON.getJSONObject("body");
        AnalyzeStoreBill bill = analyzeStoreBillDao.selectOneById(body.getLong("billId"));

        if (bill != null) {
            //处理爬取到的商品数据信息。插入进数据库。
            if ("200".equals(resultJSON.getString("code"))) {
                //处理数据Start
                bill.setName(body.getString("shopName"));
                bill.setCatchRemark(body.getString("remark"));
                bill.setShopId(body.getLong("shopId"));
                JSONArray products = body.getJSONArray("products");
                for (int i = 0; i < products.size(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    AnalyzeStoreDetail detail = new AnalyzeStoreDetail();
                    detail.setAnalyzeStoreBillId(bill.getId());
                    detail.setCreateDate(new Date());
                    detail.setMemberId(bill.getMemberId());
                    detail.setProductName(product.getString("name"));
                    detail.setProductPrice(product.getDouble("price"));
                    detail.setProductSaleQuantity(product.getInteger("saleQuantity"));
                    detail.setProductTaobaoId(product.getString("id"));
                    detail.setSourceData(product.toString());
                    detail.setStoreName(bill.getName());
                    detail.setUserId(0L);
                    analyzeStoreDetailDao.insert(detail);
                }
                //处理数据End
            } else {
                bill.setStatus(BillStatus.FAILURE.getType());
                bill.setRemark(bill.getRemark() + "，爬取信息：" + resultJSON.getString("msg"));
            }
            bill.setDoneDate(new Date());
            bill.setStatus(BillStatus.DONE.getType());
            analyzeStoreBillDao.update(bill);
        }
    }

    /**
     * 发送爬取任务至爬虫服务。
     * @param storeUrl 店铺Url
     * @param memberId 会员Id
     * @param billId 订单Id
     */
    private void sendToSpiderServiceAsyn(String storeUrl, Long memberId, Long billId) {
        JSONObject requestJSON = new JSONObject();
        requestJSON.put("shopUrl", storeUrl);
        requestJSON.put("memberId", memberId);
        requestJSON.put("billId", billId);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),
                requestJSON.toString());
        Request request = new Request.Builder()
                .url(SPIDER_URL)//请求的url
                .post(requestBody)
                .build();

        //创建/Call
        Call call = httpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(new Callback() {
            //请求错误回调方法
            @Override
            public void onFailure(Call call, IOException e) {
                logger.info("sendToSpiderService status ===>Failure"
                        + "^_^storeUrl=" + storeUrl
                        + "^_^memberId=" + memberId
                        + "^_^billId=" + billId);
                logger.info("sendToSpiderService error ===>" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                logger.info("sendToSpiderService status ===>" + response.isSuccessful()
                        + "^_^storeUrl=" + storeUrl
                        + "^_^memberId=" + memberId
                        + "^_^billId=" + billId);
            }
        });
    }

    /**
     * 获取指定会员生成过的分析店铺单List。
     * @param memberId
     * @param page
     * @param quantity
     * @return 
     */
    public List<AnalyzeStoreBill> billAndDetailsList(Long memberId, Integer page, Integer quantity){
        return analyzeStoreBillDao.selectByMemberId(memberId, true, page, quantity);
    }
    
    /**
     * 获取指定分析店铺单详情
     * @param orderId
     * @return 
     */
    public AnalyzeStoreBill selectOneBillAndDetails(Long orderId, Long memberId){
        return analyzeStoreBillDao.selectOneById(orderId, memberId, true);
    }
    
    public long countBillByMemberId(Long memberId){
        return analyzeStoreBillDao.count(memberId);
    }
}
