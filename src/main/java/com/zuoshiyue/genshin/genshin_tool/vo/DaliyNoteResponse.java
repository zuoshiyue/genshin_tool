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

    @JsonProperty("current_resin")
    private Integer currentResin;
    @JsonProperty("max_resin")
    private Integer maxResin;
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
    @JsonProperty("is_extra_task_reward_received")
    private Boolean isExtraTaskRewardReceived;
    @JsonProperty("remain_resin_discount_num")
    private Integer remainResinDiscountNum;
    @JsonProperty("resin_discount_num_limit")
    private Integer resinDiscountNumLimit;
    @JsonProperty("current_expedition_num")
    private Integer currentExpeditionNum;
    @JsonProperty("max_expedition_num")
    private Integer maxExpeditionNum;
    @JsonProperty("expeditions")
    private List<ExpeditionsDTO> expeditions;
    @JsonProperty("current_home_coin")
    private Integer currentHomeCoin;
    @JsonProperty("max_home_coin")
    private Integer maxHomeCoin;
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
        @JsonProperty("avatar_side_icon")
        private String avatarSideIcon;
        @JsonProperty("status")
        private String status;
        @JsonProperty("remained_time")
        private String remainedTime;
    }
}
