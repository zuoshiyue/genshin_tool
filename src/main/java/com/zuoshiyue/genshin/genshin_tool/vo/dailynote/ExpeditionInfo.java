package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpeditionInfo {
    private Integer currentExpeditionNum;
    private Integer maxExpeditionNum;
    private Boolean isExtraTaskRewardReceived;



}
