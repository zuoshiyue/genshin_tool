package com.zuoshiyue.genshin.genshin_tool.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zuoshiyue
 * @date 2022/8/10 16:14
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @NotBlank
    private String roleId;

    @NotBlank
    private String cookie;

    /**
     * 冒险者名称
     */
    private String nickname;
    /**
     * 冒险者世界等级
     */
    private Integer level;
}
