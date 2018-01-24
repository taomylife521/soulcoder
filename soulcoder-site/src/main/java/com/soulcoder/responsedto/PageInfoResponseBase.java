/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.responsedto;

/**
 * Created by Aministrator on 2018-01-11.
 * 分页响应类
 */
public class PageInfoResponseBase<T> extends  ResponseBase {


    public int draw;

    /// <summary>
    /// 总数
    /// </summary>
    public int recordsTotal;

    /// <summary>
    ///过滤后的条数
    /// </summary>
    public int recordsFiltered;

    public T data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }



    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
