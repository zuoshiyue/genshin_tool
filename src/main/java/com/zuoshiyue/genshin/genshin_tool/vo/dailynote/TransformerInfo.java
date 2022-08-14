package com.zuoshiyue.genshin.genshin_tool.vo.dailynote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransformerInfo {
    private Boolean reached;
    private String transformerReachedDesc;
    private Long recoveryTime;
    private Integer recoveryPTC;


}
