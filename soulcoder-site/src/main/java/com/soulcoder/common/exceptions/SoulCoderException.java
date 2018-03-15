/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.common.exceptions;

/**
 * Created by Aministrator on 2018-01-08.
 */
//自定义异常
public class SoulCoderException extends Exception {

    //异常消息
    public String message;
    ThreadLocal<String> local = new ThreadLocal<String>();


    public SoulCoderException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
