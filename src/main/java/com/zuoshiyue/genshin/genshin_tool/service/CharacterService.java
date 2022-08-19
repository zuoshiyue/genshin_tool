package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.repository.CharacterRepository;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import com.zuoshiyue.genshin.genshin_tool.vo.CharacterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author zuoshyiue
 */
@Slf4j
@Service
public class CharacterService {

    @Resource
    private CharacterRepository characterRepository;

    @Resource
    private AccountCacheService accountCacheService;

    public void getCharacterInfo() {
        Account account = accountCacheService.getAccount();
        if (Objects.isNull(account) || StringUtils.isEmpty(account.getRoleId()) || StringUtils.isEmpty(account.getCookie())) {
            log.error("缓存用户信息为空");
            return;
        }
        CharacterResponse character = characterRepository.getCharacter(account.getRoleId(), account.getCookie());
        CharacterResponse.RoleDTO role = character.getRole();
        List<CharacterResponse.AvatarsDTO> avatars = character.getAvatars();


    }
}
