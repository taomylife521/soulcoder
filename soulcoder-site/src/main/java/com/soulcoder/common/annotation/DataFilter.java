/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.common.annotation;

import java.lang.annotation.*;

/**
 * Created by Aministrator on 2018-01-15.
 * 数据过滤
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**表的别名*/
    String tableAlias() default "";

    /** true 没有本部门数据权限,也能查询本人数据*/
    boolean user() default  true;

}
