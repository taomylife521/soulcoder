/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Aministrator on 2018-01-11.
 */
@Validated
public class Req_UserList extends  PageInfoRequestBase{



    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    @JSONField(name="usercreatetimemin")
    private String userCreateTimeMin;

    @JSONField(name="usercreatetimemax")
    private String userCreateTimeMax;

    @JSONField(name="mobile")
    private String mobile;


    @Email
    @JSONField(name="email")
    private String email;

//    @JSONField(name="realname")
    private String realname;

    @JSONField(name="orderby")
    private String orderby;

    @JSONField(name="deptid")
    private String deptid;

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }



    public String getUserCreateTimeMin() {
        return userCreateTimeMin;
    }

    public void setUserCreateTimeMin(String userCreateTimeMin) {
        this.userCreateTimeMin = userCreateTimeMin;
    }

    public String getUserCreateTimeMax() {
        return userCreateTimeMax;
    }

    public void setUserCreateTimeMax(String userCreateTimeMax) {
        this.userCreateTimeMax = userCreateTimeMax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realname;
    }

    public void setRealName(String realName) {
        this.realname = realName;
    }
}
