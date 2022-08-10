package com.zuoshiyue.genshin.genshin_tool.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

/**
 * @auther wangts
 * @create 2021/6/9
 **/
public abstract class BaseController {

    public String checkParam(BindingResult bindingResult) {
        if (hasParamErrors(bindingResult)) {
            return getParamResult(bindingResult);
        }
        return "";
    }

    public boolean hasParamErrors(BindingResult bindingResult) {
        return bindingResult.hasErrors();
    }

    public String getParamResult(BindingResult bindingResult) {
        String field = Optional.ofNullable(bindingResult.getFieldError())
                .map(FieldError::getField).orElse(StringUtils.EMPTY);
        String message = Optional.ofNullable(bindingResult.getFieldError())
                .map(FieldError::getDefaultMessage).orElse(StringUtils.EMPTY);
        return field + message;
    }
}