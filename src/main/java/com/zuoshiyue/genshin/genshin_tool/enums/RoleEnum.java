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
    ROLE_INFO_1(1, "鹿野院平藏", "shikanoin_heizou", "heizou", "https://img-static.mihoyo.com/communityweb/upload/96a14409d6f41a7532b0a4f99dff02d1.png"),
    ROLE_INFO_2(2, "久岐忍", "kuki_shinobu", "shinobu", "https://img-static.mihoyo.com/communityweb/upload/f5e86466be78e3aa5b4de9302b3894f0.png"),
    ROLE_INFO_3(3, "夜兰", "yelan", "yelan", "https://img-static.mihoyo.com/communityweb/upload/c9947a19d1f9f1a8ad95d478957f6438.png"),
    ROLE_INFO_4(4, "神里绫人", "kamisato_ayato", "ayato", "https://img-static.mihoyo.com/communityweb/upload/261c1f924502b97fcdee16a63ec455cd.png"),
    ROLE_INFO_5(5, "八重神子", "yae_miko", "yae", "https://img-static.mihoyo.com/communityweb/upload/3f3333e65ddfa475fee74877bd410526.png"),
    ROLE_INFO_6(6, "云堇", "yunjin", "yunjin", "https://img-static.mihoyo.com/communityweb/upload/2b88dc71beca9f3e0e4248e8456213f0.png"),
    ROLE_INFO_7(7, "申鹤", "shenhe", "shenhe", "https://img-static.mihoyo.com/communityweb/upload/5a022501d4e34aec571cad65d667b5db.png"),
    ROLE_INFO_8(8, "荒龙一斗", "arataki_itto", "itto", "https://img-static.mihoyo.com/communityweb/upload/0a69843c2aa5b941f122605a8097e8b6.png"),
    ROLE_INFO_9(9, "五郎", "gorou", "gorou", "https://img-static.mihoyo.com/communityweb/upload/9c5c107bf7c217660c22fe13a1b71fe6.png"),
    ROLE_INFO_10(10, "托马", "thoma", "thoma", "https://img-static.mihoyo.com/communityweb/upload/05a3df42355d4424a38ff1cdfbaf6e5c.png"),
    ROLE_INFO_11(11, "珊瑚宫心海", "sangonomiya_kokomi", "kokomi", "https://img-static.mihoyo.com/communityweb/upload/ca38485367778c82961deea9b4fcfe8a.png"),
    ROLE_INFO_12(12, "埃洛伊", "", "", ""),
    ROLE_INFO_13(13, "九条裟罗", "kujou_sara", "kujou", "https://img-static.mihoyo.com/communityweb/upload/9da76677b8d690e5be3fbbe01cbe12bf.png"),
    ROLE_INFO_14(14, "雷电将军", "raiden_shogun", "shogun", "https://img-static.mihoyo.com/communityweb/upload/798ca4a21e5cb4313b9d3b0ceec7b7d6.png"),
    ROLE_INFO_15(15, "早柚", "sayu", "sayu", "https://img-static.mihoyo.com/communityweb/upload/d51a7361bbf35342b43c7801d254d016.png"),
    ROLE_INFO_16(16, "宵宫", "yoimiya", "yoimiya", "https://img-static.mihoyo.com/communityweb/upload/a8dffd89a6558efaf726c1fae960f4ec.png"),
    ROLE_INFO_17(17, "神里绫华", "kamisato_ayaka", "ayaka", "https://img-static.mihoyo.com/communityweb/upload/77b466232303de73e16cadb5271d9ae2.png"),
    ROLE_INFO_18(18, "枫原万叶", "kaedehara_kazuha", "kazuha", "https://img-static.mihoyo.com/communityweb/upload/8cb8be7ef963882482385aeee6c803df.png"),
    ROLE_INFO_19(19, "优菈", "eula", "eula", "https://img-static.mihoyo.com/communityweb/upload/36f79d8af573250c70345d10d6fc5dd0.png"),
    ROLE_INFO_20(20, "烟绯", "yanfei", "yanfei", "https://img-static.mihoyo.com/communityweb/upload/92f9a25c45ed7de6056def887710e2be.png"),
    ROLE_INFO_21(21, "罗莎莉亚", "rosaria", "rosaria", "https://img-static.mihoyo.com/communityweb/upload/fdfdf4068de504e448b53592cb0653a1.png"),
    ROLE_INFO_22(22, "胡桃", "hutao", "hutao", "https://img-static.mihoyo.com/communityweb/upload/ac024f9aeb9c69fba82e02d861b9ca15.png"),
    ROLE_INFO_23(23, "魈", "xiao", "xiao", "https://img-static.mihoyo.com/communityweb/upload/a57113d5e6173a05f7980c978c5a2bd6.png"),
    ROLE_INFO_24(24, "甘雨", "ganyu", "ganyu", "https://img-static.mihoyo.com/communityweb/upload/6961459d4637f5c23f166e12c4da6660.png"),
    ROLE_INFO_25(25, "阿贝多", "albedo", "albedo", "https://img-static.mihoyo.com/communityweb/upload/42ae758f3246a9bc209dd059fa6ae324.png"),
    ROLE_INFO_26(26, "钟离", "zhongli", "zhongli", "https://img-static.mihoyo.com/communityweb/upload/6ccdb0f1f61743895ac049702f74a507.png"),
    ROLE_INFO_27(27, "辛焱", "xinyan", "xinyan", "https://img-static.mihoyo.com/communityweb/upload/66e1406f0a83b6bb77fe45070d4da42c.png"),
    ROLE_INFO_28(28, "达达利亚", "tartaglia", "tartaglia", "https://img-static.mihoyo.com/communityweb/upload/1358947fa39f0e7fe02373b15bee4a98.png"),
    ROLE_INFO_29(29, "迪奥娜", "diona", "diona", "https://img-static.mihoyo.com/communityweb/upload/4c46a63cf3805b2c588d9857402a8d31.png"),
    ROLE_INFO_30(30, "可莉", "klee", "klee", "https://img-static.mihoyo.com/communityweb/upload/222b847170feb3f2babcc1bd4f0e30dd.png"),
    ROLE_INFO_31(31, "温迪", "venti", "venti", "https://img-static.mihoyo.com/communityweb/upload/52de23f1b1a060e4ccaa8b24c1305dd9.png"),
    ROLE_INFO_32(32, "凝光", "ningguang", "ningguang", "https://img-static.mihoyo.com/communityweb/upload/43c2bf44e066f3f763d0456100d6c2a6.png"),
    ROLE_INFO_33(33, "安柏", "amber", "amber", "https://img-static.mihoyo.com/avatar/avatar40005.png"),
    ROLE_INFO_34(34, "菲谢尔", "fischl", "fischl", "https://img-static.mihoyo.com/communityweb/upload/59e62c79aefddf9d72a2a7e0af2a7e1f.png"),
    ROLE_INFO_35(35, "砂糖", "sucrose", "sucrose", "https://img-static.mihoyo.com/communityweb/upload/ab5ebe41f1b04d2aa7500fbfbeebcf10.png"),
    ROLE_INFO_36(36, "丽莎", "lisa", "lisa", "https://img-static.mihoyo.com/communityweb/upload/5ce4e33ded0adad933201cd305551f8f.png"),
    ROLE_INFO_37(37, "诺艾尔", "noelle", "noelle", "https://img-static.mihoyo.com/communityweb/upload/b1493c45ae9c4c47877e5e8297046f90.png"),
    ROLE_INFO_38(38, "莫娜", "mona", "mona", "https://img-static.mihoyo.com/communityweb/upload/ce727afdf54b4c35b9533dd933e6b2ae.png"),
    ROLE_INFO_39(39, "凯亚", "kaeya", "kaeya", "https://img-static.mihoyo.com/avatar/avatar40001.png"),
    ROLE_INFO_40(40, "班尼特", "bennett", "bennett", "https://img-static.mihoyo.com/communityweb/upload/2872edd31066251e2430110fea06152a.png"),
    ROLE_INFO_41(41, "刻晴", "keqing", "keqing", "https://img-static.mihoyo.com/communityweb/upload/97734c89374997c7c87d5af5f7442171.png"),
    ROLE_INFO_42(42, "芭芭拉", "barbara", "barbara", "https://img-static.mihoyo.com/communityweb/upload/2adac6e4e0195c39d90696955f9a7902.png"),
    ROLE_INFO_43(43, "北斗", "beidou", "beidou", "https://img-static.mihoyo.com/communityweb/upload/5be773c6f876732c0b32e5401e4fc834.png"),
    ROLE_INFO_44(44, "重云", "chongyun", "chongyun", "https://img-static.mihoyo.com/communityweb/upload/cdbd8f33c917b27844f73623d423afe9.png"),
    ROLE_INFO_45(45, "空", "aether", "aether", "https://img-static.mihoyo.com/avatar/avatar40025.png"),
    ROLE_INFO_46(46, "荧", "lumine", "lumine", "https://img-static.mihoyo.com/avatar/avatar40017.png"),
    ROLE_INFO_47(47, "七七", "qiqi", "qiqi", "https://img-static.mihoyo.com/communityweb/upload/e910bdba0b8c2123c405a710f5d75387.png"),
    ROLE_INFO_48(48, "迪卢克", "diluc", "diluc", "https://img-static.mihoyo.com/communityweb/upload/38a67cbf6a0bf5feadde8bde74025041.png"),
    ROLE_INFO_49(49, "琴", "jean", "jean", "https://img-static.mihoyo.com/communityweb/upload/ec4e226f47a169749d96433dd63f391e.png"),
    ROLE_INFO_50(50, "香菱", "xiangling", "xiangling", "https://img-static.mihoyo.com/avatar/avatar40030.png"),
    ROLE_INFO_51(51, "行秋", "xingqiu", "xingqiu", "https://img-static.mihoyo.com/communityweb/upload/40dc988f4e89aea54dc412b6ed60a548.png"),
    ROLE_INFO_52(52, "雷泽", "razor", "razor", "https://img-static.mihoyo.com/communityweb/upload/7537c48f03523f00c8dcb0e3297aaa1a.png"),
    ;
    private int id;
    private String cnName;
    private String enName;
    private String expeditionName;
    private String avatarIcon;


    private static Map<String, RoleEnum> initMap = new HashMap<>();

    static {
        Stream.of(RoleEnum.values()).forEach(enumValue -> initMap.put(enumValue.getExpeditionName().trim().toLowerCase(), enumValue));
    }

    public static RoleEnum getByEnName(String enName) {
        enName = Safes.of(enName).trim().toLowerCase();
        return initMap.getOrDefault(enName, null);
    }
}
