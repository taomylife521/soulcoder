package com.soulcoder.pojo;

import java.util.Date;


/**
 * The type Sys role.
 *
 * @author
 */
public class SysRole extends  BaseEntity {
    private String rolename;

    private String roledescription;

    private transient Integer isdel;

    private Integer deptid;


    /**
     * 排序号
     */
    private int ordernum;

    /**
     * 是否展开
     */
    private Boolean isopen;

    private String deptname;

    private Date createtime;
    private  Integer createby;
    private Boolean isleaf;
    public Boolean getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Boolean isleaf) {
        this.isleaf = isleaf;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }



    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }



    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }





    /**
     * Gets rolename.
     *
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * Sets rolename.
     *
     * @param rolename the rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * Gets roledescription.
     *
     * @return the roledescription
     */
    public String getRoledescription() {
        return roledescription;
    }

    /**
     * Sets roledescription.
     *
     * @param roledescription the roledescription
     */
    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription;
    }

    /**
     * Gets isdel.
     *
     * @return the isdel
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * Sets isdel.
     *
     * @param isdel the isdel
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     * Gets createtime.
     *
     * @return the createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Sets createtime.
     *
     * @param createtime the createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRole other = (SysRole) that;
        return (this.getRolename() == null ? other.getRolename() == null : this.getRolename().equals(other.getRolename()))
            && (this.getRoledescription() == null ? other.getRoledescription() == null : this.getRoledescription().equals(other.getRoledescription()))
            && (this.getIsdel() == null ? other.getIsdel() == null : this.getIsdel().equals(other.getIsdel()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRolename() == null) ? 0 : getRolename().hashCode());
        result = prime * result + ((getRoledescription() == null) ? 0 : getRoledescription().hashCode());
        result = prime * result + ((getIsdel() == null) ? 0 : getIsdel().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rolename=").append(rolename);
        sb.append(", roledescription=").append(roledescription);
        sb.append(", isdel=").append(isdel);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
