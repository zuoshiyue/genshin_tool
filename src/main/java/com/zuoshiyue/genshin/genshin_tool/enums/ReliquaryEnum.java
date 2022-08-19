package com.zuoshiyue.genshin.genshin_tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zuoshyiue
 * 圣遗物枚举
 */

@AllArgsConstructor
@Getter
public enum ReliquaryEnum {
    FLOWER("生之花", 1, "https://s1.ax1x.com/2022/08/19/vrHZmF.png"),
    PLUME("死之羽", 2, "https://s1.ax1x.com/2022/08/19/vrHKYR.png"),
    SANDS("时之沙", 3, "https://s1.ax1x.com/2022/08/19/vrHlSx.png"),
    GOBLET("空之杯", 4, "https://s1.ax1x.com/2022/08/19/vrHmTJ.png"),
    CIRCLE("理之冠", 5, "https://s1.ax1x.com/2022/08/19/vrHAyT.png"),

    ;

    private String name;
    private Integer pos;
    private String icon;
}
