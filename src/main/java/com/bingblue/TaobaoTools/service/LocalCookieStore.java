/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 *
 * @author hong
 */
public class LocalCookieStore {

    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final LocalCookieManager manager = new LocalCookieManager();
//    private final LocalCookieService service = new LocalCookieService();

    public LocalCookieStore(Map cookies) {
        this.cookies = cookies;
    }

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
            add(urlHost, cookie);
        }
    }

    public void add(HttpUrl url, Cookie cookie) {
        add(url.host(), cookie);
    }

    public void add(String urlHost, Cookie cookie) {

        String name = getCookieToken(cookie);

        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(urlHost)) {
                cookies.put(urlHost, new ConcurrentHashMap<String, Cookie>());
            }
            cookies.get(urlHost).put(name, cookie);
        } else {
            if (cookies.containsKey(urlHost)) {
                cookies.get(urlHost).remove(name);
            }
        }

        //持久化到数据库中
//        LocalCookie localCookie = manager.cookieToLocalCookie(cookie);
//        service.insert(urlHost, localCookie);
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
        //数据库中数据删除
//        service.removeAll();
        cookies.clear();
        return true;
    }
    public boolean removeAll(String urlHost) {
        //数据库中数据删除
//        service.removeAll();
        if(cookies.containsKey(urlHost)){
            cookies.remove(urlHost);
        }
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
//            service.remove(urlHost, manager.cookieToLocalCookie(cookie));
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
}
