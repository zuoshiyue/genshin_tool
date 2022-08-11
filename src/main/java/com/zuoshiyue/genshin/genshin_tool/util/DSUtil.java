package com.zuoshiyue.genshin.genshin_tool.util;

import org.apache.commons.codec.digest.DigestUtils;

import static com.zuoshiyue.genshin.genshin_tool.common.account.AccountConfig.SERVER;

/**
 * @author zuoshiyue
 * @date 2022/8/3 17:08
 * @desc
 **/
public class DSUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";



    private static final String SIGN_FORMAT = "salt=xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs&t=%s&r=%s&b=&q=role_id=%s&server=" + SERVER;



    public static String getDS(String roleId) {
        String timestamp = "" + System.currentTimeMillis() / 1000;
        String randomStr = randomIntFromInt(100000, 200000);
        String sign = DigestUtils.md5Hex(String.format(SIGN_FORMAT, timestamp, randomStr, roleId));
        return timestamp + "," + randomStr + "," + sign;
    }


    private static String randomIntFromInt(int min, int max) {
        int v = (int) (Math.random() * (max - min + 1) + min);
        return String.valueOf(v);
    }

}
