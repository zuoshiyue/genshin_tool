package com.zuoshiyue.genshin.genshin_tool.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DailyNoteRepositoryTest {


    @Resource
    private DailyNoteRepository dailyNoteRepository;

    @Test
    void getDailyNote() {
        String dailyNote = dailyNoteRepository.getDailyNote();
        System.out.println(dailyNote);
    }
}