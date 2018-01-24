/*
 * Copyright (c) 2018.???写????????????????!
 */

package com.soulcoder.common.utils;

import com.soulcoder.requestdto.RequestBase;
import com.soulcoder.responsedto.R;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Aministrator on 2018-01-10.
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
    /**
     * 泛型校验方法
     * @param object
     * @param groups
     */
    public static <T extends RequestBase> R validateEntity(T  object, Class<?>... groups)    {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<T> constraint = (ConstraintViolation<T>)constraintViolations.iterator().next();
            //throw new ValidationSoulCoderException(constraint.getMessage());
            return R.failed(constraint.getMessage());
        }
        return R.ok();
    }
}
