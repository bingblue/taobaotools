/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.TaobaoWordMapper;
import com.bingblue.TaobaoTools.pojo.TaobaoWord;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class TaobaoWordDao {
    @Resource
    private TaobaoWordMapper mapper;
    
    public void insert(TaobaoWord taobaoWord){
        taobaoWord.setCreateDate(new Date());
        mapper.insert(taobaoWord);
    }
    
    public void update(TaobaoWord taobaoWord){
        mapper.updateByPrimaryKey(taobaoWord);
    }
    
}
