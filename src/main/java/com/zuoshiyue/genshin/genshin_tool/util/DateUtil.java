package com.zuoshiyue.genshin.genshin_tool.util;

/**
 * @author zuoshiyue
 * @date 2022/8/8 14:34
 * @desc
 **/
public class DateUtil {
    /**
     *  秒转为时分秒  7200 -》 02:00:00
     * @param time
     * @return
     */
    public static String transFom(final int time) {
        int hh = time / 3600;
        int mm = (time % 3600) / 60;
        int ss = (time % 3600) % 60;
        return (hh < 10 ? ("0" + hh) : hh) + ":" + (mm < 10 ? ("0" + mm) : mm) + ":" + (ss < 10 ? ("0" + ss) : ss);
    }
}
