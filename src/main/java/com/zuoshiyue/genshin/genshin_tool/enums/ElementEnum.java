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
    CRYO("Cryo", "冰"),
    GEO("Geo", "岩"),
    ELECTRO("Electro", "雷"),
    HYDRO("Hydro", "水"),
    PYRO("Pyro", "火"),
    ANEMO("Anemo", "风"),

    ;

    private String name;
    private String desc;

    public static final Map<String, ElementEnum> ENUM_MAP = new LinkedHashMap<>();

    static {
        ENUM_MAP.putAll(Arrays.stream(ElementEnum.values()).collect(Collectors.toMap(ElementEnum::getName, item -> item)));
    }

    public static final ElementEnum getElementEnumByName(String name){
        return ENUM_MAP.get(name);
    }

}
