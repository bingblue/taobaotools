/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.pojo.User;


/**
 *
 * @author pdmilk
 */
public interface IUserService {
    public User getUserById(int userId);
}
