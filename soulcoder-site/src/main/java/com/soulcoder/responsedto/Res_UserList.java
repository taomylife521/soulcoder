/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.responsedto;

import com.soulcoder.pojo.SysUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aministrator on 2018-01-11.
 */
public class Res_UserList extends ResponseBase {
    private List<SysUser> userList = new ArrayList<SysUser>();

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }
}
