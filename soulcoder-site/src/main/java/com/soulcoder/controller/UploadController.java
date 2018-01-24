/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

package com.soulcoder.controller;

import com.soulcoder.common.utils.ValidatorUtils;
import com.soulcoder.enums.ResponseStatus;
import com.soulcoder.requestdto.Req_Upload;
import com.soulcoder.responsedto.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Aministrator on 2018-01-22.
 */
@RestController
@RequestMapping("sys")
public class UploadController extends AbstractController {

    @RequestMapping("upload")
    public R upload(Req_Upload request)
    {
        R res = ValidatorUtils.validateEntity((request));
        if(res.getStatus() != ResponseStatus.Success.getIndex()){
            return res;
        }
        return R.ok();
    }
}
