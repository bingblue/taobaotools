/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import com.bingblue.TaobaoTools.dao.LocalCookieDao;
import com.bingblue.TaobaoTools.pojo.LocalCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 * @author hong
 */
public class LocalCookieStore implements ApplicationListener<ContextRefreshedEvent> {

    private Map<String, ConcurrentHashMap<String, Cookie>> cookies = new HashMap<>();
    
    @Resource
    private LocalCookieDao localCookieDao;

    private String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, List<Cookie> cookieList) {
        for (Cookie cookie : cookieList) {
            add(url, cookie);
        }
    }

    public void add(String urlHost, List<Cookie> cookieList) {
        for (Cookie cookie : cookieList) {
            add(urlHost, cookie, true);
        }
    }

    public void add(HttpUrl url, Cookie cookie) {
        add(url.host(), cookie, true);
    }

    public void add(String urlHost, Cookie cookie, boolean insertDb) {

        String name = getCookieToken(cookie);

        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(urlHost)) {
                cookies.put(urlHost, new ConcurrentHashMap<>());
            }
            cookies.get(urlHost).put(name, cookie);
        } else {
            if (cookies.containsKey(urlHost)) {
                cookies.get(urlHost).remove(name);
            }
        }

        if (insertDb) {
            //持久化到数据库中
            LocalCookie localCookie = localCookieDao.okHttp3CookieToLocalCookie(type, urlHost, cookie);
            localCookieDao.insertOrUpdate(localCookie);
        }
    }

    public List<Cookie> get(String urlHost) {
        ArrayList<Cookie> ret = new ArrayList();
        if (cookies.containsKey(urlHost)) {
            ret.addAll(cookies.get(urlHost).values());
        }
        return ret;
    }

    public List<Cookie> get(HttpUrl url) {
        return get(url.host());
    }

    public boolean removeAll() {
        cookies.clear();
        //数据库中数据删除
        localCookieDao.remove(type, null);
        return true;
    }

    public boolean removeAll(String urlHost) {
        if (cookies.containsKey(urlHost)) {
            cookies.remove(urlHost);
        }
        //数据库中数据删除
        localCookieDao.remove(type, urlHost);
        return true;
    }

    public boolean removeAll(HttpUrl url) {
        return removeAll(url.host());
    }

    public boolean remove(HttpUrl url, Cookie cookie) {
        return remove(url.host(), cookie);
    }

    public boolean remove(String urlHost, Cookie cookie) {

        String name = getCookieToken(cookie);

        if (cookies.containsKey(urlHost) && cookies.get(urlHost).containsKey(name)) {
            cookies.get(urlHost).remove(name);

            //删除数据库数据
            LocalCookie localCookie = localCookieDao.okHttp3CookieToLocalCookie(type, urlHost, cookie);
            localCookieDao.remove(localCookie);
            return true;
        } else {
            return false;
        }
    }

    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList();
        for (String key : cookies.keySet()) {
            ret.addAll(cookies.get(key).values());
        }
        return ret;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        List<LocalCookie> localCookieList = localCookieDao.select(type, null);
        localCookieList.forEach((localCookie) -> {
            Cookie c = localCookieDao.localCookieToOkHttp3Cookie(localCookie);
            //add
            this.add(localCookie.getHost(), c, false);
        });
    }
    
    private String type;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

}
