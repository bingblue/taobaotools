/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.UserMapper;
import com.bingblue.TaobaoTools.pojo.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
