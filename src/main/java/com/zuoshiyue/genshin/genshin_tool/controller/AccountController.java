package com.zuoshiyue.genshin.genshin_tool.controller;

import com.zuoshiyue.genshin.genshin_tool.service.AccountCacheService;
import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author lupengfei
 * @date 2022/8/10 16:24
 * @desc
 **/
@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Resource
    private AccountCacheService accountCacheService;

    @Resource
    private CacheManager cacheManager;

    @PostMapping("/save")
    public String save(@RequestBody @Valid Account account, BindingResult bindingResult) {
        String errorMsg = checkParam(bindingResult);
        if (StringUtils.isNotBlank(errorMsg)) {
            return errorMsg;
        }
        Account account1 = accountCacheService.cacheAccount(account.getRoleId(), account.getCookie());
        if (Objects.isNull(account1)) {
            return "fail";
        }
        return JsonUtil.toJson(account1);
    }

    @GetMapping("/get")
    public String get(@Valid @NotBlank String roleId, BindingResult bindingResult) {
        String errorMsg = checkParam(bindingResult);
        if (StringUtils.isNotBlank(errorMsg)) {
            return errorMsg;
        }
        Account account = accountCacheService.getAccount(roleId);
        if (Objects.isNull(account)) {
            return "fail";
        }
        return JsonUtil.toJson(account);
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String hello = cacheManager.getCache("hello").getName();
        request.setAttribute("user", hello);
        return "views/index";
    }
}
