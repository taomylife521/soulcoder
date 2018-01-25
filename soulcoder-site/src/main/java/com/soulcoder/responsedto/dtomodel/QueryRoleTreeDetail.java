package com.soulcoder.responsedto.dtomodel;

/**
 * Created by Aministrator on 2018-01-25.
 */
public class QueryRoleTreeDetail {
    /**
     * 上级id
     */
    private String parentid;
    private boolean isRole;

    public boolean isRole() {
        return isRole;
    }

    public void setRole(boolean role) {
        isRole = role;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 部门名称或角色名称
     */
    private String name;

    /**
     * 排序号
     */
    private int ordernum;

    /**
     * 上级部门名称
     */
    private String parentname;

    /**
     * 上级部门名称
     */
    private String id;

    /**
     * 是否展开
     */
    private Boolean isopen;

    private  Integer createby;

    /**
     * 描述
     */
    private  String description;


}
