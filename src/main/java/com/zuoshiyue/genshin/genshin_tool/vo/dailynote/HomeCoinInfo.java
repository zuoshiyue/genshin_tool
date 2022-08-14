package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeCoinInfo {
    private Integer maxHomeCoin;
    private Integer currentHomeCoin;
    private String homeCoinRecoveryTime;
    private String recoveryTimeDesc;

}
