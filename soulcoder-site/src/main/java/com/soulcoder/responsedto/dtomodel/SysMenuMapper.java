package com.soulcoder.responsedto.dtomodel;

import com.soulcoder.pojo.SysMenu;

/**
 * Created by Aministrator on 2018-01-31.
 */
public class SysMenuMapper extends SysMenu {
    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    private boolean chkDisabled=false;
}
