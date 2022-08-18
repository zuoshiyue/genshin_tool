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
        private String nickname;
        private String region;
        private Integer level;
    }

    @NoArgsConstructor
    @Data
    public static class AvatarsDTO {
        private Integer id;
        private String image;
        private String icon;
        private String name;
        private String element;
        private Integer fetter;
        private Integer level;
        private Integer rarity;
        private WeaponDTO weapon;
        private List<ReliquariesDTO> reliquaries;
        private List<ConstellationsDTO> constellations;
        @JsonProperty("actived_constellation_num")
        private Integer activedConstellationNum;
        private List<?> costumes;
        private Object external;

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
