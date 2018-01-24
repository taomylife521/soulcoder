/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aministrator on 2018-01-20.
 */
@Controller
public class UnAuthorController {
    @RequestMapping("/unauthor")
    public String unAuthor(){
        return "unauthor";
    }
}
