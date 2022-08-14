package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuoshiyue
 * @date 2022/8/11 10:14
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {
    /**
     * 委托总数
     */
    private Integer totalTaskNum;
    /**
     * 委托完成数量
     */
    private Integer finishedTaskNum;
    /**
     * 每日委托额外奖励是否领取
     */
    private Boolean isExtraTaskRewardReceived;
}
