package com.zuoshiyue.genshin.genshin_tool.repository;

import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.DaliyNoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import static com.zuoshiyue.genshin.genshin_tool.config.AccountConfig.*;
import static com.zuoshiyue.genshin.genshin_tool.util.DSUtil.getDS;

/**
 * @author lupengfei
 * @date 2022/8/3 17:21
 * @desc 返回原神便笺信息
 **/
@Slf4j
@Component
public class DailyNoteRepository {

    private static final String DAILY_NOTE_URL = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/dailyNote?server=%s&role_id=%s";

    public DaliyNoteResponse getDailyNote() {

        try {
            String fullUrl = String.format(DAILY_NOTE_URL, SERVER, ROLE_ID);
            String httpResponse = getHttpResponse(fullUrl, COOKIE);
            Map<String, Object> stringObjectMap = JsonUtil.ofMap(httpResponse, String.class, Object.class);
            if (stringObjectMap.get("retcode") == null || (int)stringObjectMap.get("retcode") != 0){
               return null;
            }
            Object data = stringObjectMap.get("data");
            return JsonUtil.of(JsonUtil.toJson(data), DaliyNoteResponse.class);
        } catch (Exception e) {
            log.info("getDailyNote error", e);
            return null;
        }
    }


    public static String getHttpResponse(String allConfigUrl, String cookies) {
        BufferedReader in = null;
        StringBuffer result;
        try {
            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("DS", getDS());
            /**
             # 1:  ios
             # 2:  android
             # 4:  pc web
             # 5:  mobile web
             */
            connection.setRequestProperty("x-rpc-client_type", "5");
            connection.setRequestProperty("x-rpc-app_version", "2.33.1");
            connection.setRequestProperty("x-rpc-channel", "baidu");
            connection.setRequestProperty("Referer", "https://webstatic.mihoyo.com/");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/103.0.5060.134");

            connection.setRequestProperty("Cookie", cookies);
            connection.connect();
            result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
