/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.interceptor;

import com.alibaba.fastjson.JSON;
import com.soulcoder.common.exceptions.SoulCoderException;
import com.soulcoder.responsedto.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Aministrator on 2018-01-08.
 * 全局异常处理
 */
public class GlobalExceptionInterceptor implements HandlerExceptionResolver {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionInterceptor.class);
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        logger.error(JSON.toJSONString(ex));
        ex.printStackTrace();
        SoulCoderException customException = null;

        //判断下是否是自定义异常
        if(ex instanceof SoulCoderException) {
            customException = (SoulCoderException) ex;
        } else {
            //构造一个自定义异常
            customException = new SoulCoderException("系统繁忙,请稍候重试");
        }

        ModelAndView modelAndView = new ModelAndView();
        String xReq = httpServletRequest.getHeader("x-requested-with");
        if (StringUtils.isNotBlank(xReq) && "XMLHttpRequest".equalsIgnoreCase(xReq)) {//判断是不是ajax请求
            // 如果是ajax直接返回
            R result=  R.exception("system is busy!",ex);
            try {
               PrintWriter printWriter= httpServletResponse.getWriter();

                printWriter.write(JSON.toJSONString(result));
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {


            modelAndView.addObject("message", customException.getMessage());
            modelAndView.setViewName("/WEB-INF/page/error.jsp");


        }
        return modelAndView;


    }
}

