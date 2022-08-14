package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResinInfo {
    private Integer maxResin;
    private Integer currentResin;
    private String resinRecoveryTime;
    private String recoveryTimeDesc;

}
