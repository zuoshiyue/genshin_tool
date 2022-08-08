package com.zuoshiyue.genshin.genshin_tool.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DailyNoteServiceTest {

    @Resource
    private DailyNoteService dailyNoteService;

    @Test
    void getDailyNoteInfo() {
        dailyNoteService.getDailyNoteInfo();
    }
}