package com.zuoshiyue.genshin.genshin_tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zuoshyiue
 */

@AllArgsConstructor
@Getter
public enum WeaponTypeEnum {
    TYPE_1(1, "单手剑"),
    TYPE_10(10, "法器"),
    TYPE_11(11, "双手剑"),
    TYPE_12(12, "弓"),
    TYPE_13(13, "长柄武器"),


    ;
    private Integer type;
    private String name;
}
