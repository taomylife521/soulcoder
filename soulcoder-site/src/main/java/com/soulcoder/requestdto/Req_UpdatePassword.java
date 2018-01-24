/*
 * Copyright (c) 2018.閹碉拷閺堝鍞惍浣哄閺夊啫缍婄紓鏍垳閼板懏澧嶉張锟�!
 */

package com.soulcoder.requestdto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Aministrator on 2018-01-09.
 */
public class Req_UpdatePassword {
    /**
    * @Author:Aministrator
    * @Description:閺冄冪槕閻拷
    * @Date:2018-01-09
    * @param
    * @return
    */

    @NotEmpty
    @Size(min = 6,max = 20,message = "{item.length.errormsg}")
    private String password;

    /**
     * @Author:Aministrator
     * @Description:绾喛顓婚弮褍鐦戦惍锟�
     * @Date:2018-01-09
     * @param
     * @return
     */
    private String confirmpassword;

    /**
     * @Author:Aministrator
     * @Description:閺傛澘鐦戦惍锟�
     * @Date:2018-01-09
     * @param
     * @return
     */
    private String newpassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
