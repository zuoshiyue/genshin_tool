package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuoshyiue
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpeditionAvatarInfo {
    private String status;
    private Long coverTime;
    private String coverTimeDesc;
    private String name;
    private String icon;
}
