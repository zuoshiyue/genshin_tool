package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.enums.ElementEnum;
import com.zuoshiyue.genshin.genshin_tool.repository.CharacterRepository;
import com.zuoshiyue.genshin.genshin_tool.util.Safes;
import com.zuoshiyue.genshin.genshin_tool.vo.Account;
import com.zuoshiyue.genshin.genshin_tool.vo.CharacterResponse;
import com.zuoshiyue.genshin.genshin_tool.vo.character.CharacterInfo;
import com.zuoshiyue.genshin.genshin_tool.vo.character.WeaponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<CharacterInfo> getCharacterInfo() {
        Account account = accountCacheService.getAccount();
        if (Objects.isNull(account) || StringUtils.isEmpty(account.getRoleId()) || StringUtils.isEmpty(account.getCookie())) {
            log.error("缓存用户信息为空");
            return Collections.emptyList();
        }
        CharacterResponse character = characterRepository.getCharacter(account.getRoleId(), account.getCookie());
        if (Objects.isNull(character)) {
            log.error("获取账号角色信息为空");
            return Collections.emptyList();
        }
        CharacterResponse.RoleDTO role = character.getRole();
        if (Objects.nonNull(role)) {
            account.setNickname(role.getNickname());
            account.setLevel(role.getLevel());
            accountCacheService.cacheAccount(account);
        }
        List<CharacterResponse.AvatarsDTO> avatars = character.getAvatars();
        return Safes.of(avatars).stream().filter(Objects::nonNull).map(v -> CharacterInfo.builder()
                        .name(v.getName())
                        .level(v.getLevel())
                        .rarity(v.getRarity())
                        .element(ElementEnum.getElementEnumByName(v.getElement()).getDesc())
                        .elementIcon(ElementEnum.getElementEnumByName(v.getElement()).getIcon())
                        .icon(v.getIcon())
                        .image(v.getImage())
                        .weaponName(v.getWeapon().getName())
                        .weaponInfo(WeaponInfo.builder()
                                .name(v.getWeapon().getName())
                                .icon(v.getWeapon().getIcon())
                                .rarity(v.getWeapon().getRarity())
                                .level(v.getWeapon().getLevel())
                                .promoteLevel(v.getWeapon().getPromoteLevel())
                                .typeName(v.getWeapon().getTypeName())
                                .desc(v.getWeapon().getDesc())
                                .build())
                        .activedConstellationNum(v.getActivedConstellationNum())
                        .build())
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(CharacterInfo::getRarity, Comparator.reverseOrder()).thenComparing(CharacterInfo::getLevel, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
