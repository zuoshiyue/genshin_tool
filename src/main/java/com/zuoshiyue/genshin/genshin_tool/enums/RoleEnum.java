package com.zuoshiyue.genshin.genshin_tool.enums;

import com.zuoshiyue.genshin.genshin_tool.util.Safes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author lupengfei
 * @date 2022/8/9 09:52
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_INFO_1(1,"鹿野院平藏","shikanoin_heizou",""),
    ROLE_INFO_2(2,"久岐忍","kuki_shinobu",""),
    ROLE_INFO_3(3,"夜兰","yelan",""),
    ROLE_INFO_4(4,"神里绫人","kamisato_ayato",""),
    ROLE_INFO_5(5,"八重神子","yae_miko",""),
    ROLE_INFO_6(6,"云堇","yunjin",""),
    ROLE_INFO_7(7,"申鹤","shenhe",""),
    ROLE_INFO_8(8,"荒龙一斗","arataki_itto",""),
    ROLE_INFO_9(9,"五郎","gorou",""),
    ROLE_INFO_10(10,"托马","thoma",""),
    ROLE_INFO_11(11,"珊瑚宫心海","sangonomiya_kokomi",""),
    ROLE_INFO_12(12,"埃洛伊","",""),
    ROLE_INFO_13(13,"九条裟罗","kujou_sara",""),
    ROLE_INFO_14(14,"雷电将军","raiden_shogun",""),
    ROLE_INFO_15(15,"早柚","sayu",""),
    ROLE_INFO_16(16,"宵宫","yoimiya",""),
    ROLE_INFO_17(17,"神里绫华","kamisato_ayaka",""),
    ROLE_INFO_18(18,"枫原万叶","kaedehara_kazuha",""),
    ROLE_INFO_19(19,"优菈","eula",""),
    ROLE_INFO_20(20,"烟绯","yanfei",""),
    ROLE_INFO_21(21,"罗莎莉亚","rosaria",""),
    ROLE_INFO_22(22,"胡桃","hutao",""),
    ROLE_INFO_23(23,"魈","xiao",""),
    ROLE_INFO_24(24,"甘雨","ganyu",""),
    ROLE_INFO_25(25,"阿贝多","albedo",""),
    ROLE_INFO_26(26,"钟离","zhongli",""),
    ROLE_INFO_27(27,"辛焱","xinyan",""),
    ROLE_INFO_28(28,"达达利亚","tartaglia",""),
    ROLE_INFO_29(29,"迪奥娜","diona",""),
    ROLE_INFO_30(30,"可莉","klee",""),
    ROLE_INFO_31(31,"温迪","venti",""),
    ROLE_INFO_32(32,"凝光","ningguang",""),
    ROLE_INFO_33(33,"安柏","amber",""),
    ROLE_INFO_34(34,"菲谢尔","fischl",""),
    ROLE_INFO_35(35,"砂糖","sucrose",""),
    ROLE_INFO_36(36,"丽莎","lisa",""),
    ROLE_INFO_37(37,"诺艾尔","noelle",""),
    ROLE_INFO_38(38,"莫娜","mona",""),
    ROLE_INFO_39(39,"凯亚","kaeya",""),
    ROLE_INFO_40(40,"班尼特","bennett",""),
    ROLE_INFO_41(41,"刻晴","keqing",""),
    ROLE_INFO_42(42,"芭芭拉","barbara",""),
    ROLE_INFO_43(43,"北斗","beidou",""),
    ROLE_INFO_44(44,"重云","chongyun",""),
    ROLE_INFO_45(45,"空","aether",""),
    ROLE_INFO_46(46,"荧","lumine",""),
    ROLE_INFO_47(47,"七七","qiqi",""),
    ROLE_INFO_48(48,"迪卢克","diluc",""),
    ROLE_INFO_49(49,"琴","jean",""),
    ROLE_INFO_50(50,"香菱","xiangling",""),
    ROLE_INFO_51(51,"行秋","xingqiu",""),
    ROLE_INFO_52(52,"雷泽","razor",""),
;
    private int id;
    private String cnName;
    private String enName;
    private String avatarIcon;

    private static Map<String, RoleEnum> initMap = new HashMap<>();

    static {
        Stream.of(RoleEnum.values()).forEach(enumValue -> initMap.put(enumValue.getEnName().trim().toLowerCase(), enumValue));
    }

    public RoleEnum getByEnName(String enName){
        enName = Safes.of(enName).trim().toLowerCase();
        return initMap.getOrDefault(enName, null);
    }
}
