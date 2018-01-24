package com.soulcoder.dao;

import com.soulcoder.pojo.SysRole;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * The interface Sys role dao.
 */
public interface SysRoleDao extends BaseDao<SysRole> {


    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(SysRole record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(SysRole record);

    /**
     * Update by example selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByExampleSelective(@Param("record") SysRole record);

    /**
     * Update by example int.
     *
     * @param record the record
     * @return the int
     */
    int updateByExample(@Param("record") SysRole record);
}
