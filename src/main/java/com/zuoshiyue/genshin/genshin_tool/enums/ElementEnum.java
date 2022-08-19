package com.zuoshiyue.genshin.genshin_tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zuoshyiue
 * 角色元素属性枚举
 */
@AllArgsConstructor
@Getter
public enum ElementEnum {
    CRYO("Cryo", "冰", "https://s1.ax1x.com/2022/08/19/vrHPWq.png"),
    GEO("Geo", "岩", "https://s1.ax1x.com/2022/08/19/vrHew4.png"),
    ELECTRO("Electro", "雷", "https://s1.ax1x.com/2022/08/19/vrHEOU.png"),
    HYDRO("Hydro", "水", "https://s1.ax1x.com/2022/08/19/vrHuk9.png"),
    PYRO("Pyro", "火", "https://s1.ax1x.com/2022/08/19/vrHMf1.png"),
    ANEMO("Anemo", "风", "https://s1.ax1x.com/2022/08/19/vrHFS0.png"),
    DENDRO("dendro", "草", "https://s1.ax1x.com/2022/08/19/vrHklV.png"),

    ;

    private String name;
    private String desc;
    private String icon;

    public static final Map<String, ElementEnum> ENUM_MAP = new LinkedHashMap<>();

    static {
        ENUM_MAP.putAll(Arrays.stream(ElementEnum.values()).collect(Collectors.toMap(ElementEnum::getName, item -> item)));
    }

    public static final ElementEnum getElementEnumByName(String name) {
        return ENUM_MAP.get(name);
    }

}
