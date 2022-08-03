package com.zuoshiyue.genshin.genshin_tool.common.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lupengfei
 * @date 2022/8/3 20:01
 * @desc
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue {
    private Object key;

    private Object value;
}
