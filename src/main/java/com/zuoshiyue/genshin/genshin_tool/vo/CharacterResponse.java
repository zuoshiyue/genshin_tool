package com.zuoshiyue.genshin.genshin_tool.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zuoshyiue
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
         * 冒险者世界等级
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
         * 元素属性名称
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
            /**
             * 武器名称
             */
            private String name;
            /**
             * 图标
             */
            private String icon;
            /**
             * 类型
             */
            private Integer type;
            /**
             * 星级
             */
            private Integer rarity;
            /**
             * 等级
             */
            private Integer level;
            /**
             * 精练等级
             */
            @JsonProperty("promote_level")
            private Integer promoteLevel;
            /**
             * 类型名称
             */
            @JsonProperty("type_name")
            private String typeName;
            /**
             * 武器描述
             */
            private String desc;
            /**
             * 词缀等级
             */
            @JsonProperty("affix_level")
            private Integer affixLevel;
        }

        @NoArgsConstructor
        @Data
        public static class ReliquariesDTO {
            private Integer id;
            /**
             * 圣遗物名称
             */
            private String name;
            /**
             * 图片
             */
            private String icon;
            /**
             * 位置
             */
            private Integer pos;
            /**
             * 稀有度级别
             */
            private Integer rarity;
            /**
             * 等级
             */
            private Integer level;
            /**
             * 套装属性
             */
            private SetDTO set;
            /**
             * 位置名称
             */
            @JsonProperty("pos_name")
            private String posName;

            @NoArgsConstructor
            @Data
            public static class SetDTO {
                private Integer id;
                /**
                 * 套装效果名称
                 */
                private String name;
                /**
                 * 套装属性
                 */
                private List<AffixesDTO> affixes;

                @NoArgsConstructor
                @Data
                public static class AffixesDTO {
                    /**
                     * 效果触发套装数
                     */
                    @JsonProperty("activation_number")
                    private Integer activationNumber;
                    /**
                     * 效果说明
                     */
                    private String effect;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class ConstellationsDTO {
            private Integer id;
            /**
             * 名称
             */
            private String name;
            /**
             * 图标
             */
            private String icon;
            /**
             * 效果说明你过
             */
            private String effect;
            /**
             * 是否生效
             */
            @JsonProperty("is_actived")
            private Boolean isActived;
            /**
             * 排序
             */
            private Integer pos;
        }
    }
}
