/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.requestdto;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by Aministrator on 2018-01-22.
 */
public class Req_Upload  extends  RequestBase{
    @NotNull
    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    /**
    * 上传请求参数
    */
    public Map<String,Object> map;
}
