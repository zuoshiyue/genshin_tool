package com.zuoshiyue.genshin.genshin_tool.repository;

import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.DailyNoteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DailyNoteRepositoryTest {


    @Resource
    private DailyNoteRepository dailyNoteRepository;

    @Test
    void getDailyNote() {
        DailyNoteResponse dailyNote = dailyNoteRepository.getDailyNote();
        System.out.println(JsonUtil.toJson(dailyNote));
    }
}