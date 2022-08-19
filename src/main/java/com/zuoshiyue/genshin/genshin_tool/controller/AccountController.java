package com.zuoshiyue.genshin.genshin_tool.controller;

import com.zuoshiyue.genshin.genshin_tool.service.AccountCacheService;
import com.zuoshiyue.genshin.genshin_tool.service.CharacterService;
import com.zuoshiyue.genshin.genshin_tool.service.DailyNoteService;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import com.zuoshiyue.genshin.genshin_tool.vo.character.CharacterInfo;
import com.zuoshiyue.genshin.genshin_tool.vo.dailynote.DailyNoteInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author zuoshiyue
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
    private DailyNoteService dailyNoteService;

    @Resource
    private CacheManager cacheManager;

    @Resource
    private CharacterService characterService;

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
//        return "redirect:account/save";
        getCharacter(request);
        String dailyNote = this.getDailyNote(request);
        return dailyNote;
    }

    @RequestMapping("getcCharacter")
    public String getCharacter(HttpServletRequest request) {
        List<CharacterInfo> characterInfos = characterService.getCharacterInfo();
        if (!CollectionUtils.isEmpty(characterInfos)) {
            request.setAttribute("characterInfo", characterInfos);
        }
        return this.get(request);
    }

    @RequestMapping("/getDailyNote")
    public String getDailyNote(HttpServletRequest request) {
        DailyNoteInfo dailyNoteInfo = dailyNoteService.getDailyNoteInfo();
        if (Objects.nonNull(dailyNoteInfo)) {
            request.setAttribute("dailyNoteInfo", dailyNoteInfo);
        }
        return this.get(request);
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
}
