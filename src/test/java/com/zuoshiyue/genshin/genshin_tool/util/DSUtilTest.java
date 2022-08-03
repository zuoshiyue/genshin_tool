package com.zuoshiyue.genshin.genshin_tool.util;

import org.junit.jupiter.api.Test;

class DSUtilTest {

    @Test
    void randomStrGen() {
        int length = 6;
        String s = DSUtil.randomStrGen(length);
        System.out.println(s);
    }
}