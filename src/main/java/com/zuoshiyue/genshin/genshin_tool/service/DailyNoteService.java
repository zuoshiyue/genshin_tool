package com.zuoshiyue.genshin.genshin_tool.service;

import com.zuoshiyue.genshin.genshin_tool.repository.DailyNoteRepository;
import com.zuoshiyue.genshin.genshin_tool.util.DateUtil;
import com.zuoshiyue.genshin.genshin_tool.util.Safes;
import com.zuoshiyue.genshin.genshin_tool.vo.DailyNoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

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

    private static final int TRANSFORMER_START_TIME = 7 * 86400;

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
        getHomeCoin(dailyNote, now);
        //派遣
        getExpeditionInfo(dailyNote, now);
        //半价周本
        getWeeklyExplorationInfo(dailyNote, now);
        //参量质变
        getTransformerInfo(dailyNote, now);

    }

    /**
     * 派遣任务
     */
    private void getExpeditionInfo(DailyNoteResponse dailyNote, long now) {
        //当前探索派遣人数
        Integer currentExpeditionNum = Optional.ofNullable(dailyNote.getCurrentExpeditionNum()).orElse(-1);
        //探索派遣限制
        Integer maxExpeditionNum = Optional.ofNullable(dailyNote.getMaxExpeditionNum()).orElse(0);
        //每日委托额外奖励领取情况
        Boolean isExtraTaskRewardReceived = Optional.ofNullable(dailyNote.getIsExtraTaskRewardReceived()).orElse(false);
        //派遣人员信息
        List<DailyNoteResponse.ExpeditionsDTO> expeditions = dailyNote.getExpeditions();
        String result = "";
        if (maxExpeditionNum < 0) {
            result += "未派遣，请添加派遣角色";
        } else {
            result += String.format("共%s人", Optional.of(currentExpeditionNum).orElse(0));
        }

        AtomicLong minCoverTime = new AtomicLong(Safes.of(expeditions).stream().filter(Objects::nonNull).findFirst()
                .map(DailyNoteResponse.ExpeditionsDTO::getRemainedTime).map(NumberUtils::toLong).orElse(0L));
        AtomicBoolean hasFinished = new AtomicBoolean(false);
        Safes.of(expeditions).stream().filter(Objects::nonNull)
                .forEach(expedition -> {
                    String status = expedition.getStatus();
                    long remainedTime = NumberUtils.toLong(expedition.getRemainedTime(), 0);
                    if (remainedTime < minCoverTime.get()) {
                        minCoverTime.set(remainedTime);
                    }
                    if (StringUtils.equalsIgnoreCase(status, "Finished")) {
                        hasFinished.set(true);
                    }
                    String clock = DateUtil.getClock(remainedTime, now);
                    String avatarSideIcon = expedition.getAvatarSideIcon();
                    String[] split = avatarSideIcon.split("/");
                    String sp = split[split.length - 1];
                    String[] split1 = sp.split("\\.");
                    String s = split1[0];
                    String[] s1 = s.split("_");
                    String name = s1[s1.length - 1];
                    System.out.println("Name:" + name + "\t时间：" + clock);
                });
        if (hasFinished.get()) {
            result += ", 派遣任务奖励可领取";
        } else if (minCoverTime.get() > 0) {
            String clock = DateUtil.getClock(minCoverTime.get(), now);
            result += ", " + clock;
        }

        System.out.println("派遣探索：" + result);

    }

    /**
     * 参量质变仪
     */
    private void getTransformerInfo(DailyNoteResponse dailyNote, long now) {
        DailyNoteResponse.TransformerDTO.RecoveryTimeDTO recoveryTimeDTO = Optional.ofNullable(dailyNote).map(DailyNoteResponse::getTransformer).map(DailyNoteResponse.TransformerDTO::getRecoveryTime).orElse(null);
        if (Objects.isNull(recoveryTimeDTO)) {
            return;
        }
        String result = "";
        Boolean reached = Optional.ofNullable(recoveryTimeDTO.getReached()).orElse(false);
        if (reached) {
            result = "可用";
        } else {
            if (Objects.nonNull(recoveryTimeDTO.getDay()) && recoveryTimeDTO.getDay() > 0) {
                result += String.format("剩%s天", recoveryTimeDTO.getDay());
            } else if (Objects.nonNull(recoveryTimeDTO.getHour()) && recoveryTimeDTO.getHour() > 0) {
                result += String.format("剩%s时", recoveryTimeDTO.getHour());
            } else if (Objects.nonNull(recoveryTimeDTO.getMinute()) && recoveryTimeDTO.getMinute() > 0) {
                result += String.format("剩%s分", recoveryTimeDTO.getMinute());
            } else if (Objects.nonNull(recoveryTimeDTO.getSecond()) && recoveryTimeDTO.getSecond() > 0) {
                result += String.format("剩%s秒", recoveryTimeDTO.getSecond());
            }
        }
        System.out.println("参量质变仪状态：" + result);
        this.getProgressBar(recoveryTimeDTO, now);
    }

    /**
     * 参量质变仪 - 进度条
     */
    private void getProgressBar(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO recoveryTimeDTO, long now) {
        int day = Optional.ofNullable(recoveryTimeDTO).map(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO::getDay).orElse(0);
        int hour = Optional.ofNullable(recoveryTimeDTO).map(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO::getHour).orElse(0);
        int minute = Optional.ofNullable(recoveryTimeDTO).map(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO::getMinute).orElse(0);
        int second = Optional.ofNullable(recoveryTimeDTO).map(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO::getSecond).orElse(0);
        int startranscurrent = TRANSFORMER_START_TIME - day * 86400 - hour * 3600 - minute * 60 - second;
        if (Optional.ofNullable(recoveryTimeDTO).map(DailyNoteResponse.TransformerDTO.RecoveryTimeDTO::getReached).orElse(false)) {
            startranscurrent = TRANSFORMER_START_TIME;
        }
        int barNum = new BigDecimal(startranscurrent * 1.00 / TRANSFORMER_START_TIME).setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        System.out.println("参量质变仪 - 进度条：" + barNum + "%");
    }

    /**
     * 周本
     */
    private void getWeeklyExplorationInfo(DailyNoteResponse dailyNote, long now) {
        //减半上限
        Integer resinDiscountNumLimit = dailyNote.getResinDiscountNumLimit();
        //减半剩余数量
        Integer remainResinDiscountNum = dailyNote.getRemainResinDiscountNum();
        System.out.println("周本树脂减半次数：" + resinDiscountNumLimit + "\t剩余次数：" + remainResinDiscountNum);
    }

    /**
     * 家园宝钱
     */
    private void getHomeCoin(DailyNoteResponse dailyNote, long now) {
        //宝钱最大值
        Integer maxHomeCoin = dailyNote.getMaxHomeCoin();
        //宝钱当前值
        Integer currentHomeCoin = dailyNote.getCurrentHomeCoin();
        //宝钱预计恢复时间
        String homeCoinRecoveryTime = dailyNote.getHomeCoinRecoveryTime();
        System.out.println("宝钱：" + currentHomeCoin + "/" + maxHomeCoin + "\t预计完成时间：" + DateUtil.getClock(NumberUtils.toLong(homeCoinRecoveryTime), now));
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
        System.out.println("树脂：" + currentResin + "/" + maxResin + "\t预计完成时间：" + DateUtil.getClock(NumberUtils.toLong(resinRecoveryTime), now));
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
        System.out.println("每日委托：" + finishedTaskNum + "/" + totalTaskNum + "\t额外奖励：" + BooleanUtils.toString(isExtraTaskRewardReceived, "领取", "未领取"));
    }
}
