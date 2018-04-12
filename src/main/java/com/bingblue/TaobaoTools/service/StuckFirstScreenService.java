/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.alibaba.fastjson.JSONObject;
import com.bingblue.TaobaoTools.controller.Tools;
import com.bingblue.TaobaoTools.dao.StuckFirstScreenBillDao;
import com.bingblue.TaobaoTools.dao.TaobaoWordDao;
import com.bingblue.TaobaoTools.pojo.BillStatus;
import com.bingblue.TaobaoTools.pojo.StuckFirstScreenBill;
import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import com.bingblue.TaobaoTools.pojo.TaobaoWordType;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * 卡首屏
 *
 * @author SayMing
 */
@Service()
public class StuckFirstScreenService {

    private Logger logger = Logger.getLogger(StuckFirstScreenService.class);

    //https://s.m.taobao.com/h5?q=洗发水&nid=529164550758
    private static final String MOBILE_BASE_URL = "https://s.m.taobao.com/h5?nid=%s&q=%s";
    //https://s.taobao.com/search?q=洗发水&nid_up=529164550758&openP4P=true
    private static final String PC_BASE_URL = "https://s.taobao.com/search?nid_up=%s&q=%s&openP4P=true";
    //编码格式
    private static final String TAOBAO_CHARACTER_ENCODING = "UTF-8";
    @Resource
    private SearchProductByKeywordsTaobaoService crawlTaobaoService;
    @Resource
    private TaobaoTpwdCreateService taobaoTpwdCreateService;
    @Resource
    private StuckFirstScreenBillDao stuckFirstScreenBillDao;
    @Resource
    private TaobaoWordDao taobaoWordDao;
    @Resource
    private TaskBillService taskBillService;

    /**
     * 生成卡首屏计划
     *
     * @param sync 是否同步
     * @param stuckFirstScreenBill 卡首屏单-包含TaobaoWord
     * @return 卡首屏单
     */
    public StuckFirstScreenBill generatePlan(boolean sync, StuckFirstScreenBill stuckFirstScreenBill) {
        taobaoWordDao.insert(stuckFirstScreenBill.getTaobaoWord());
        stuckFirstScreenBill.setStatus(BillStatus.CREATING.toString());
        stuckFirstScreenBill.setTaobaoWordId(stuckFirstScreenBill.getTaobaoWord().getId());
        stuckFirstScreenBillDao.insert(stuckFirstScreenBill);

        if (sync) {//同步
            generate(stuckFirstScreenBill);
        } else {//异步
            try {
                taskBillService.submit(stuckFirstScreenBill);
            } catch (InterruptedException ex) {
                logger.error(ex);
            }
        }
        return stuckFirstScreenBill;
    }

    /**
     * 生成淘口令
     *
     * @param stuckFirstScreenBill 待生成淘口令的卡首屏单
     * @return 生成好的卡首屏单含TaobaoWord
     */
    public StuckFirstScreenBill generate(StuckFirstScreenBill stuckFirstScreenBill) {
        TaobaoWord taobaoWord = stuckFirstScreenBill.getTaobaoWord();
        String stayTpwdUrl = generateMobileUrl(stuckFirstScreenBill.getProductId(), stuckFirstScreenBill.getKeywords());
        if (stayTpwdUrl != null) {
            taobaoWord.setUrl(stayTpwdUrl);
            taobaoWord = taobaoTpwdCreateService.create(taobaoWord);
            if (taobaoWord.getStatus().equals(TaobaoWordType.CREATING.toString())) {
                stuckFirstScreenBill.setStatus(BillStatus.CREATING.toString());
            } else if (taobaoWord.getStatus().equals(TaobaoWordType.FAILURE.toString())) {
                stuckFirstScreenBill.setStatus(BillStatus.FAILURE.toString());
            } else if (taobaoWord.getStatus().equals(TaobaoWordType.OVER.toString())) {
                stuckFirstScreenBill.setStatus(BillStatus.OVER.toString());
            } else if (taobaoWord.getStatus().equals(TaobaoWordType.STAY_CREATE.toString())) {
                stuckFirstScreenBill.setStatus(BillStatus.STAY_CREATE.toString());
            }
        } else {
            stuckFirstScreenBill.setStatus(BillStatus.FAILURE.toString());
            stuckFirstScreenBill.setRemark("生成淘口令的URL为空！");
        }
        stuckFirstScreenBill.setTaobaoWord(taobaoWord);
        stuckFirstScreenBillDao.update(stuckFirstScreenBill);
        return stuckFirstScreenBill;
    }

    /**
     * 生成卡首屏手机端url
     *
     * @param taobaoProductId 淘宝商品id
     * @param keywords 关键词
     * @return 卡首屏url
     */
    public String generateMobileUrl(String taobaoProductId, String keywords) {
        //根据关键词爬取淘宝搜索前20条结果，并追加到taobaoProductId之后，用逗号隔开。
        List<String> productIds = crawlTaobaoService.crawl(keywords);

        StringBuilder sb = new StringBuilder(taobaoProductId);
        for (String pid : productIds) {
            sb.append(",").append(pid);
        }

        String url = null;
        try {
            url = String.format(MOBILE_BASE_URL, sb.toString(), URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        return url;
    }

    /**
     * 生成pc端卡首屏url
     *
     * @param taobaoProductId 淘宝商品id
     * @param keywords 关键词
     * @return 卡首屏url
     */
    public String generatePcUrl(String taobaoProductId, String keywords) {
        String url = null;
        try {
            url = String.format(PC_BASE_URL, taobaoProductId, URLEncoder.encode(keywords, TAOBAO_CHARACTER_ENCODING));
        } catch (UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        return url;
    }

    /**
     * 获取指定会员的卡首屏生成单
     *
     * @param memberId 会员Id
     * @param page 第几页
     * @param quantity 需要多少返回数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 结果数据list
     */
    public List<StuckFirstScreenBill> list(Long memberId, Integer page, Integer quantity, Date startDate, Date endDate) {
        return stuckFirstScreenBillDao.select(memberId, page, quantity, startDate, endDate);
    }

    /**
     * 计算指定会员当下有多少卡首屏单
     *
     * @param memberId 会员Id
     * @return 数量
     */
    public long countStuckFirstScreenBillByMemberId(Long memberId, Date startDate, Date endDate) {
        return stuckFirstScreenBillDao.count(memberId, startDate, endDate);
    }

    public StuckFirstScreenBill getOneHasTaobaoWord(Long id, Long memberId) {
        return stuckFirstScreenBillDao.selectOneHasTaobaoWord(id, memberId);
    }

    /**
     * 从url中获取宝贝Id
     * https://item.taobao.com/item.htm?spm=a230r.1.14.36.b4b75aep8p04W&id=548798597452&ns=1&abbucket=15#detail
     *
     * @param url
     * @return
     */
    public static final String findProductIdFromUrl(String url) {
        String id = Tools.getQueryString(url, "id");
        System.out.println("id=" + id);
        return id;
    }

}
