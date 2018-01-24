/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.common.filter;

import com.soulcoder.partener.shiro.ShiroUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aministrator on 2018-01-11.
 * 閺嶏繝鐛欓惂璇茬秿閺勵垰鎯佺搾鍛
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        if(!ShiroUtils.getSubject().isAuthenticated()){//閺勵垰鎯佸鑼病鐠併倛鐦�
            if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
                //婵″倹鐏夐弰鐥慾ax鐠囬攱鐪伴崫宥呯安婢剁繝绱伴張澶涚礉x-requested-with
                response.setHeader("session-status", "timeout");//閸︺劌鎼锋惔鏂裤仈鐠佸墽鐤唖ession閻樿埖锟斤拷
                return;
            }
        }
        filterChain.doFilter(request,servletResponse);
    }

    public void destroy() {

    }
}
