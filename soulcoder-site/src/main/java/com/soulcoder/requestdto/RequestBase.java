/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.requestdto;

import com.soulcoder.partener.shiro.ShiroUtils;

/**
 * Created by Aministrator on 2018-01-11.
 */
public class RequestBase {
    public Integer loginId= ShiroUtils.getUserEntity().getId();
}
