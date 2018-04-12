/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.dao;

import com.bingblue.TaobaoTools.mapper.LocalCookieMapper;
import com.bingblue.TaobaoTools.pojo.LocalCookie;
import com.bingblue.TaobaoTools.pojo.LocalCookieExample;
import java.util.List;
import javax.annotation.Resource;
import okhttp3.Cookie;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SayMing
 */
@Repository
public class LocalCookieDao {

    @Resource
    private LocalCookieMapper mapper;

    public void insert(LocalCookie localCookie) {
        mapper.insert(localCookie);
    }

    public void insertOrUpdate(LocalCookie localCookie) {
        int count = mapper.updateByTypeOtherSelective(localCookie);
        if (count <= 0) {
            mapper.insert(localCookie);
        }
    }

    public void remove(LocalCookie localCookie) {
        LocalCookieExample ex = new LocalCookieExample();
        ex.createCriteria().andDomainEqualTo(localCookie.getDomain())
                .andHostEqualTo(localCookie.getHost())
                .andNameEqualTo(localCookie.getName())
                .andTypeEqualTo(localCookie.getType());
        mapper.deleteByExample(ex);
    }

    public void remove(String type, String host) {
        LocalCookieExample ex = new LocalCookieExample();
        LocalCookieExample.Criteria c = ex.createCriteria();
        if (type != null && !type.isEmpty()) {
            c.andTypeEqualTo(type);
        }
        if (host != null && !host.isEmpty()) {
            c.andHostEqualTo(host);
        }
        mapper.deleteByExample(ex);
    }

    public List<LocalCookie> select(String type, String host) {
        LocalCookieExample ex = new LocalCookieExample();
        LocalCookieExample.Criteria c = ex.createCriteria();
        if (type != null && !type.isEmpty()) {
            c.andTypeEqualTo(type);
        }
        if (host != null && !host.isEmpty()) {
            c.andHostEqualTo(host);
        }
        return mapper.selectByExample(ex);
    }

    public LocalCookie selectOne(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * OkHttp3 Cookie 转换为本地存储Cookie
     *
     * @param type 类型
     * @param host
     * @param cookie okHttp3 cookie对象
     * @return 自定义cookie
     */
    public LocalCookie okHttp3CookieToLocalCookie(String type, String host, Cookie cookie) {
        LocalCookie localCookie = new LocalCookie();
        localCookie.setDomain(cookie.domain());
        localCookie.setExpiresAt(cookie.expiresAt());
        localCookie.setHostOnly(cookie.hostOnly());
        localCookie.setHttpOnly(cookie.httpOnly());
        localCookie.setName(cookie.name());
        localCookie.setPath(cookie.path());
        localCookie.setPersistent(cookie.persistent());
        localCookie.setSecure(cookie.secure());
        localCookie.setValue(cookie.value());
        localCookie.setType(type);
        localCookie.setHost(host);
        return localCookie;
    }

    /**
     * 本地存储Cookie 转换为OkHttp3 Cookie
     *
     * @param localCookie 自定义cookie
     * @return okHttp3 Cookie 对象
     */
    public Cookie localCookieToOkHttp3Cookie(LocalCookie localCookie) {
        Cookie.Builder builder = new Cookie.Builder()
                .expiresAt(localCookie.getExpiresAt())
                .name(localCookie.getName())
                .path(localCookie.getPath())
                .value(localCookie.getValue());
        if (localCookie.getHostOnly()) {
            builder.hostOnlyDomain(localCookie.getDomain());
        } else {
            builder.domain(localCookie.getDomain());
        }
        return builder.build();
    }

}
