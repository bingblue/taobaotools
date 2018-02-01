/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.StuckFirstScreenBillDao;
import com.bingblue.TaobaoTools.pojo.BillStatus;
import com.bingblue.TaobaoTools.pojo.ManyOrderBill;
import com.bingblue.TaobaoTools.pojo.StuckFirstScreenBill;
import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import com.bingblue.TaobaoTools.pojo.TaobaoWordBill;
import com.bingblue.TaobaoTools.pojo.TaobaoWordType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 *
 * @author SayMing
 */
@Service
public class TaskBillService implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private TaobaoTpwdCreateService taobaoTpwdCreateService;
    @Resource
    private StuckFirstScreenService stuckFirstScreenService;
    @Resource
    private StuckFirstScreenBillDao stuckFirstScreenBillDao;

    private LinkedBlockingQueue<TaobaoWord> taobaoWordLinkedBlockingQueue = new LinkedBlockingQueue<TaobaoWord>();

    private LinkedBlockingQueue<StuckFirstScreenBill> stuckFirstScreenBillLinkedBlockingQueue = new LinkedBlockingQueue<StuckFirstScreenBill>();

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void submit(TaobaoWord taobaoWord) throws InterruptedException {
        taobaoWordLinkedBlockingQueue.put(taobaoWord);
    }

    public void submit(StuckFirstScreenBill stuckFirstScreenBill) throws InterruptedException {
        stuckFirstScreenBillLinkedBlockingQueue.put(stuckFirstScreenBill);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        executorService.submit(new TaobaoWordRunnable());
        executorService.submit(new StuckFirstScreenBillRunnable());
    }

    class TaobaoWordRunnable implements Runnable {

        private Logger logger = Logger.getLogger(TaobaoWordRunnable.class);

        public void run() {
            while (true) {
                try {

                    TaobaoWord taobaoWord = taobaoWordLinkedBlockingQueue.take();

                    taobaoTpwdCreateService.create(taobaoWord);

                } catch (InterruptedException ex) {
                    logger.info("拿出队列中的TaobaoBill出错。" + ex.getMessage());
                    logger.error(ex);
                }
            }
        }
    }

    class StuckFirstScreenBillRunnable implements Runnable {

        private Logger logger = Logger.getLogger(StuckFirstScreenBillRunnable.class);

        @Override
        public void run() {
            while (true) {
                try {
                    StuckFirstScreenBill stuckFirstScreenBill = stuckFirstScreenBillLinkedBlockingQueue.take();
                    stuckFirstScreenService.generate(stuckFirstScreenBill);
                } catch (InterruptedException ex) {
                    logger.info("拿出队列中的StuckFirstScreenBill出错。" + ex.getMessage());
                    logger.error(ex);
                }
            }
        }

    }

}
