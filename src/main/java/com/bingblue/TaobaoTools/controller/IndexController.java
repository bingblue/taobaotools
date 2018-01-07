/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pdmilk
 */
@Controller
public class IndexController {
    
    @RequestMapping("/*")
    public String toIndex(HttpServletRequest request, Model model){
        return "index";
    }
    
}
