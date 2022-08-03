package com.zuoshiyue.genshin.genshin_tool.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zuoshiyue
 * @date 2022/8/3 17:08
 * @desc
 **/
public class DSUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomStrGen(int length) {
        StringBuilder result = new StringBuilder();
        int charactersLength = CHARACTERS.length();
        for (int i = 0; i < length; i++) {
            result.append(CHARACTERS.charAt((int) Math.floor(ThreadLocalRandom.current().nextDouble() * charactersLength)));
        }
        return result.toString();
    }

    public static String md5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //得到一个信息摘要器
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //获取Base64编码算法工具
        BASE64Encoder base64 = new BASE64Encoder();
        //进行编码加密
        String encode = base64.encode(md5.digest(password.getBytes("UTF-8")));
        return encode;
    }
}
