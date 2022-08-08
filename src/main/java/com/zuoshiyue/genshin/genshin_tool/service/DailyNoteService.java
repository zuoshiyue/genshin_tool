package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.repository.DailyNoteRepository;
import com.zuoshiyue.genshin.genshin_tool.util.DateUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.DailyNoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zuoshiyue
 * @date 2022/8/8 14:38
 * @desc
 **/
@Slf4j
@Service
public class DailyNoteService {

    @Resource
    private DailyNoteRepository dailyNoteRepository;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public void getDailyNoteInfo() {
        long now = System.currentTimeMillis();
        DailyNoteResponse dailyNote = dailyNoteRepository.getDailyNote();
        if (dailyNote == null) {
            log.error("未获取到便笺信息");
            return;
        }
        //每日委托
        getTaskInfo(dailyNote, now);
        //树脂
        getResinInfo(dailyNote, now);
        //宝钱
        //派遣
        //半价周本
        //参量质变

    }

    /**
     * 树脂情况
     */
    private void getResinInfo(DailyNoteResponse dailyNote, long now) {
        //树脂上限
        Integer maxResin = dailyNote.getMaxResin();
        //当前树脂数量
        Integer currentResin = dailyNote.getCurrentResin();
        //树脂预计恢复时间
        String resinRecoveryTime = dailyNote.getResinRecoveryTime();
        long recoveryTime = now + NumberUtils.toLong(resinRecoveryTime) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String format = sdf.format(new Date(recoveryTime));
        System.out.println("树脂：" + currentResin + "/" + maxResin + " 预计完成时间：" + DateUtil.getClock(NumberUtils.toLong(resinRecoveryTime), now));
    }

    /**
     * 每日委托
     */
    private void getTaskInfo(DailyNoteResponse dailyNote, long now) {
        //委托总数
        Integer totalTaskNum = dailyNote.getTotalTaskNum();
        //委托完成数量
        Integer finishedTaskNum = dailyNote.getFinishedTaskNum();
        //每日委托额外奖励是否领取
        Boolean isExtraTaskRewardReceived = dailyNote.getIsExtraTaskRewardReceived();
        System.out.println("每日委托：" + finishedTaskNum + "/" + totalTaskNum + " 额外奖励：" + BooleanUtils.toString(isExtraTaskRewardReceived, "领取", "未领取"));
    }
}
