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
public class WeaponInfo {
    private String name;
    private String icon;
    private Integer rarity;
    private Integer level;
    private Integer promoteLevel;
    private Integer typeName;
    private String desc;
}
