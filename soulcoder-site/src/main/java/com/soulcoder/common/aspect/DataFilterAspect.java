/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.common.aspect;

import com.soulcoder.common.annotation.DataFilter;
import com.soulcoder.enums.Constant;
import com.soulcoder.partener.shiro.ShiroUtils;
import com.soulcoder.pojo.SysUser;
import com.soulcoder.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Aministrator on 2018-01-15.
 */
@Aspect
@Component
public class DataFilterAspect {

    @Autowired
    private IDeptService sysDeptService;

    /**
    * @Author:切入点
    * @Description:当前执行方法上持有注解 DataFilter将被匹配
    * @Date:2018-01-15
    * @param
    * @return
    */
    @Pointcut("@annotation(com.soulcoder.common.annotation.DataFilter)")
    public void dataFilterCut(){


    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable{
        Object params =point.getArgs()[0];
        if(params != null && params instanceof Map){
           SysUser user= ShiroUtils.getUserEntity();
           //判断如果不是超级管理员，则只能查询本部门及子部门数据
            if(user.getId() != Constant.SUPER_ADMIN){
                Map map =(Map) params;
                map.put("filterSql",getFilterSql(user,point));
            }
        }
        return;
    }

    /**
     * 获取数据过滤的sql
     * */
    private Object getFilterSql(SysUser user, JoinPoint point) {

        MethodSignature signature = (MethodSignature)point.getSignature();
       DataFilter dataFilter= signature.getMethod().getAnnotation(DataFilter.class);
       String tableAlias=dataFilter.tableAlias();//获取表的别名
        if(StringUtils.isNoneBlank(tableAlias)){
            tableAlias+=".";
        }
        //获取子部门ID
        String subDeptIds = sysDeptService.getSubDeptIdList(user.getDeptid());

        StringBuilder filterSql = new StringBuilder();
        filterSql.append("and (");
        filterSql.append(tableAlias).append("dept_id in(").append(subDeptIds).append(")");

        //没有本部门数据权限，也能查询本人数据
        if(dataFilter.user()){
            filterSql.append(" or ").append(tableAlias).append("user_id=").append(user.getId());
        }
        filterSql.append(")");

        return filterSql.toString();

    }


}
