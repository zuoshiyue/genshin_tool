package com.zuoshiyue.genshin.genshin_tool.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @author zuoshiyue
 * @date 2022/8/8 14:34
 * @desc
 **/
public class DateUtil {

    private static final ZoneOffset zoneOffset = ZoneOffset.ofHours(8);

    /**
     * 秒转为时分秒  7200 -》 02:00:00
     *
     * @param time
     * @return
     */
    public static String transFom(final int time) {
        int hh = time / 3600;
        int mm = (time % 3600) / 60;
        int ss = (time % 3600) % 60;
        return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm) + ":" + (ss < 10 ? ("0" + ss) : ss);
    }

    public static String getClock(long time, long now) {
        long timeRecovery = now + time * 1000;

        LocalDateTime targetLocal = LocalDateTime.ofEpochSecond(timeRecovery/1000, 0, zoneOffset);
        LocalDateTime nowLocal = LocalDateTime.ofEpochSecond(now/1000, 0, zoneOffset);
        int nowHour = nowLocal.getHour();
        long nowMinute = nowLocal.getMinute() * 60 * 1000;
        long nowSecond = nowLocal.getSecond() * 1000;

        long tillTommorow = (24 - nowHour) * 3600 * 1000;

        long tommorow = now + tillTommorow - nowMinute - nowSecond;

        String str = "";
        if (timeRecovery < tommorow) {
            str = "今日";
        } else if (timeRecovery - tommorow > 86400000) {
            DayOfWeek dayOfWeek = targetLocal.getDayOfWeek();
            String displayName = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.getDefault());
            str = "周" + displayName;
        } else {
            str = "明日";
        }

        return str + targetLocal.getHour() + "点" + targetLocal.getMinute() + "分";
    }
}
