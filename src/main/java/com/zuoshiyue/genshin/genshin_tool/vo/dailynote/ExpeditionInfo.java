package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

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
public class ExpeditionInfo {
    private Integer currentExpeditionNum;
    private Integer maxExpeditionNum;
    private Boolean isExtraTaskRewardReceived;
    private Boolean hasFinished;
    private Long minCoverTime;
    private String minCoverTimeDesc;
    private List<ExpeditionAvatarInfo> expeditions;



}
