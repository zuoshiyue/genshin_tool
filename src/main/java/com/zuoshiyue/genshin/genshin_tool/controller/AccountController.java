package com.zuoshiyue.genshin.genshin_tool.controller;

import com.zuoshiyue.genshin.genshin_tool.service.AccountCacheService;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @RequestMapping("/save")
    public String save(HttpServletRequest request, @Valid Account account, BindingResult bindingResult) {
        String errorMsg = checkParam(bindingResult);
        if (StringUtils.isNotBlank(errorMsg)) {
            return errorMsg;
        }
        Account account1 = accountCacheService.cacheAccount(account.getRoleId(), account.getCookie());
        if (Objects.isNull(account1)) {
            return "views/error";
        }
        request.setAttribute("user", account1);

        return "views/index";
    }

    @RequestMapping("/get")
    public String get(HttpServletRequest request) {
        Account account = accountCacheService.getAccount();
        if (Objects.isNull(account)) {
            return "views/error";
        }
        request.setAttribute("user", account);
        return "views/index";
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        Cache hello = cacheManager.getCache("hello");
        request.setAttribute("user", hello.getName());
        return "views/index";
    }
}
