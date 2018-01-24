/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.requestdto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Aministrator on 2018-01-11.
 */
public class PageInfoRequestBase extends RequestBase {

    @JSONField(name="draw")
    private int draw=0;

    @JSONField(name="start")
    private int start=0;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @JSONField(name="length")
    private int length=10;


}
