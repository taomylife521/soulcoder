package com.soulcoder.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Aministrator on 2018-01-30.
 */
public interface SysRoleMenuDao {
    List<Integer> queryListByRoleId(Map<String,Object> map);
}
