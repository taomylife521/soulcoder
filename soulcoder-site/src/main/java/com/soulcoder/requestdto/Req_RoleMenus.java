package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-30.
 */
public class Req_RoleMenus extends  RequestBase {

    @JSONField(name="roleid")
    public int roleId;
}
