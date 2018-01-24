/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingblue.TaobaoTools.service;

import okhttp3.Cookie;
import com.bingblue.TaobaoTools.pojo.LocalCookie;

/**
 *
 * @author hong
 */
public class LocalCookieManager {

    /**
     * OkHttp3 Cookie 转换为本地存储Cookie
     * @param cookie
     * @return 
     */
    public LocalCookie cookieToLocalCookie(Cookie cookie) {
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
        return localCookie;
    }

    /**
     *  本地存储Cookie 转换为OkHttp3 Cookie
     * @param localCookie
     * @return 
     */
    public Cookie localCookieToCookie(LocalCookie localCookie) {
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
