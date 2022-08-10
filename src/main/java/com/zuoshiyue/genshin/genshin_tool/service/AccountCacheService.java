package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lupengfei
 * @date 2022/8/10 15:39
 * @desc
 **/
@Slf4j
@Service
public class AccountCacheService {

    @Cacheable(cacheNames = "account", key = "#roleId")
    public Account cacheAccount(String roleId, String cookie){
        if (StringUtils.isNotBlank(roleId) || StringUtils.isNotBlank(cookie)){
            return null;
        }
        return Account.builder()
                .roleId(roleId)
                .cookie(cookie)
                .build();
    }

    @CachePut(cacheNames = "account")
    public Account getAccount(String roleId) {

        return null;
    }
}
