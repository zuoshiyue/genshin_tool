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
public class DailyNoteInfo {
    TaskInfo taskInfo;
    ResinInfo resinInfo;
    HomeCoinInfo homeCoinInfo;
    ExpeditionInfo expeditionInfo;
    WeeklyExplorationInfo weeklyExplorationInfo;
    TransformerInfo transformerInfo;
}
