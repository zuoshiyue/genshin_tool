package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lupengfei
 * @date 2022/8/10 15:39
 * @desc
 **/
@Slf4j
@Service
public class AccountCacheService {

    @Resource
    private CacheManager cacheManager;

    @CachePut(cacheNames = "account", key = "'account'")
    public Account cacheAccount(String roleId, String cookie){
        if (StringUtils.isBlank(roleId) || StringUtils.isBlank(cookie)){
            return null;
        }
        return Account.builder()
                .roleId(roleId)
                .cookie(cookie)
                .build();
    }

    public Account getAccount() {
        Cache cache = cacheManager.getCache("account");
        if (Objects.isNull(cache)){
            return null;
        }
        Cache.ValueWrapper account = cache.get("account");
        if (Objects.isNull(account)){
            return null;
        }
        return JsonUtil.of(JsonUtil.toJson(account.get()), Account.class);
    }
}
