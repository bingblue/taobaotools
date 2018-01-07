/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service.impl;

import com.bingblue.TaobaoTools.dao.UserMapper;
import com.bingblue.TaobaoTools.pojo.User;
import com.bingblue.TaobaoTools.service.IUserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author pdmilk
 */
@Service("userService")
public class UserServiceImpl implements IUserService  {

    @Resource
    private UserMapper userMapper;
    
    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    
}
