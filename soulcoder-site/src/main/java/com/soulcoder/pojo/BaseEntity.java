/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.pojo;

import com.soulcoder.responsedto.ResponseBase;

import java.io.Serializable;

/**
 * Created by Aministrator on 2018-01-09.
 */
public class BaseEntity extends ResponseBase implements Serializable {

    protected  Integer id;
    protected static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
