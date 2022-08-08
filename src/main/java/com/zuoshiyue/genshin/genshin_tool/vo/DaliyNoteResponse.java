package com.zuoshiyue.genshin.genshin_tool.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lupengfei
 * @date 2022/8/8 14:05
 * @desc
 **/
@NoArgsConstructor
@Data
public class DaliyNoteResponse {

    /**
     * 当前树脂
     */
    @JsonProperty("current_resin")
    private Integer currentResin;
    /**
     * 树脂上限
     */
    @JsonProperty("max_resin")
    private Integer maxResin;
    /**
     *  树脂预计恢复时间
     */
    @JsonProperty("resin_recovery_time")
    private String resinRecoveryTime;
    /**
     * 每日委托任务完成
     */
    @JsonProperty("finished_task_num")
    private Integer finishedTaskNum;
    /**
     * 每日委托任务
     */
    @JsonProperty("total_task_num")
    private Integer totalTaskNum;
    /**
     * 每日委托额外奖励领取情况
     */
    @JsonProperty("is_extra_task_reward_received")
    private Boolean isExtraTaskRewardReceived;
    /**
     * 强敌每周减半次数剩余
     */
    @JsonProperty("remain_resin_discount_num")
    private Integer remainResinDiscountNum;
    /**
     * 强敌每周减半次数上限
     */
    @JsonProperty("resin_discount_num_limit")
    private Integer resinDiscountNumLimit;
    /**
     * 当前探索派遣人数
     */
    @JsonProperty("current_expedition_num")
    private Integer currentExpeditionNum;
    /**
     * 探索派遣限制
     */
    @JsonProperty("max_expedition_num")
    private Integer maxExpeditionNum;
    /**
     * 派遣人员详情
     */
    @JsonProperty("expeditions")
    private List<ExpeditionsDTO> expeditions;
    /**
     * 当前洞天宝钱
     */
    @JsonProperty("current_home_coin")
    private Integer currentHomeCoin;
    /**
     * 洞天宝钱上限
     */
    @JsonProperty("max_home_coin")
    private Integer maxHomeCoin;
    /**
     * 洞天宝钱预计恢复时间
     */
    @JsonProperty("home_coin_recovery_time")
    private String homeCoinRecoveryTime;
    @JsonProperty("calendar_url")
    private String calendarUrl;
    @JsonProperty("transformer")
    private TransformerDTO transformer;

    @NoArgsConstructor
    @Data
    public static class TransformerDTO {
        @JsonProperty("obtained")
        private Boolean obtained;
        @JsonProperty("recovery_time")
        private RecoveryTimeDTO recoveryTime;
        @JsonProperty("wiki")
        private String wiki;
        @JsonProperty("noticed")
        private Boolean noticed;
        @JsonProperty("latest_job_id")
        private String latestJobId;

        @NoArgsConstructor
        @Data
        public static class RecoveryTimeDTO {
            @JsonProperty("Day")
            private Integer day;
            @JsonProperty("Hour")
            private Integer hour;
            @JsonProperty("Minute")
            private Integer minute;
            @JsonProperty("Second")
            private Integer second;
            @JsonProperty("reached")
            private Boolean reached;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ExpeditionsDTO {
        /**
         * 图标
         */
        @JsonProperty("avatar_side_icon")
        private String avatarSideIcon;
        /**
         * 派遣状态
         */
        @JsonProperty("status")
        private String status;
        /**
         * 派遣剩余时间
         */
        @JsonProperty("remained_time")
        private String remainedTime;
    }
}
