package com.zuoshiyue.genshin.genshin_tool.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lupengfei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse {

    private List<AvatarsDTO> avatars;
    private RoleDTO role;

    @NoArgsConstructor
    @Data
    public static class RoleDTO {
        private String avatarUrl;
        /**
         * 冒险者名称
         */
        private String nickname;
        /**
         * 服务器区域
         */
        private String region;
        /**
         * 冒险者世界登记
         */
        private Integer level;
    }

    @NoArgsConstructor
    @Data
    public static class AvatarsDTO {
        private Integer id;
        /**
         * 立绘图
         */
        private String image;
        /**
         * icon图
         */
        private String icon;
        /**
         * 角色名称
         */
        private String name;
        /**
         * 元素属性
         */
        private String element;
        /**
         * 羁绊等级
         */
        private Integer fetter;
        /**
         * 角色等级
         */
        private Integer level;
        /**
         * 角色星级
         */
        private Integer rarity;
        /**
         * 武器信息
         */
        private WeaponDTO weapon;
        /**
         * 圣遗物信息
         */
        private List<ReliquariesDTO> reliquaries;
        /**
         * 命之座详细信息
         */
        private List<ConstellationsDTO> constellations;

        /**
         * 命之座数量
         */
        @JsonProperty("actived_constellation_num")
        private Integer activedConstellationNum;
        /**
         * 服装信息
         */
        private List<Costumes> costumes;

        @NoArgsConstructor
        @Data
        public static class Costumes {
            private Integer id;
            private String name;
            private String icon;
        }

        @NoArgsConstructor
        @Data
        public static class WeaponDTO {
            private Integer id;
            private String name;
            private String icon;
            private Integer type;
            private Integer rarity;
            private Integer level;
            @JsonProperty("promote_level")
            private Integer promoteLevel;
            @JsonProperty("type_name")
            private String typeName;
            private String desc;
            @JsonProperty("affix_level")
            private Integer affixLevel;
        }

        @NoArgsConstructor
        @Data
        public static class ReliquariesDTO {
            private Integer id;
            private String name;
            private String icon;
            private Integer pos;
            private Integer rarity;
            private Integer level;
            private SetDTO set;
            @JsonProperty("pos_name")
            private String posName;

            @NoArgsConstructor
            @Data
            public static class SetDTO {
                private Integer id;
                private String name;
                private List<AffixesDTO> affixes;

                @NoArgsConstructor
                @Data
                public static class AffixesDTO {
                    @JsonProperty("activation_number")
                    private Integer activationNumber;
                    private String effect;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class ConstellationsDTO {
            private Integer id;
            private String name;
            private String icon;
            private String effect;
            @JsonProperty("is_actived")
            private Boolean isActived;
            private Integer pos;
        }
    }
}
