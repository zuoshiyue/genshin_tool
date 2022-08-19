package com.zuoshiyue.genshin.genshin_tool.vo.character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuoshyiue
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInfo {
    private String name;
    private Integer level;
    private Integer rarity;
    private String element;
    private String elementIcon;
    private String icon;
    private String image;
    private String weaponName;
    private WeaponInfo weaponInfo;
    private Integer activedConstellationNum;
}
