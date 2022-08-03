package com.zuoshiyue.genshin.genshin_tool.repository;

import com.zuoshiyue.genshin.genshin_tool.util.DSUtil;
import com.zuoshiyue.genshin.genshin_tool.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lupengfei
 * @date 2022/8/3 17:21
 * @desc 返回原神便笺信息
 **/
@Slf4j
@Component
public class DailyNoteRepository {

    /**
     * 国服
     */
    private static final String SERVER = "cn_gf01";

    /**
     * 账户ID
     */
    private static final String ROLE_ID = "137236712";

    private static final String DAILY_NOTE_URL = "https://bbs-api-os.hoyolab.com/game_record/genshin/api/dailyNote";
    private static final String COOKIE = "mi18nLang=zh-cn; _MHYUUID=7e4813ba-a86d-41fb-9233-35c03ca98bf3; UM_distinctid=17f9267483dee-0decd15ea10b99-1b5d2620-13c680-17f9267483ef1; _ga_Q58GRM5TVC=GS1.1.1652424682.1.1.1652425283.0; _ga=GA1.2.1742244236.1647428709; _ga_45KPGN9XLN=GS1.1.1652750533.1.1.1652750545.0; ltoken=WTawnw52BlC4V4NoVSwYhQVWR4fZtxAxeZklldGh; ltuid=229525194; _gid=GA1.2.46291600.1659508684; acw_tc=0a362b5d16595323347564825e0b9db408ba7f3ef8bd2b5d4b7e40b1f787e1; _gat=1";

    public String getDailyNote() {
        String randomStr = DSUtil.randomStrGen(6);
        long timestamp = System.currentTimeMillis() / 1000;
        String signDecoder = "salt=6s25p5ox5y14umn1p61aqyyvbvvl3lrt&t=" + timestamp + "&r=" + randomStr;

        try {
            String sign = DSUtil.md5(signDecoder);
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("server",SERVER);
            paramMap.put("role_id",ROLE_ID);
            List<BasicHeader> headerList = new ArrayList<>();
            headerList.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            headerList.add(new BasicHeader("DS", timestamp + "," + randomStr + "," + sign));
            headerList.add(new BasicHeader("x-rpc-client_type", "5"));
            headerList.add(new BasicHeader("x-rpc-app_version", "2.9.1"));
            headerList.add(new BasicHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBSOversea/2.9.1"));
            headerList.add(new BasicHeader("Origin", "https://act.hoyolab.com"));
            headerList.add(new BasicHeader("Referer", "https://act.hoyolab.com/"));
            headerList.add(new BasicHeader("Cookie", COOKIE));
            Header[] headers = headerList.toArray(new Header[0]);

            String s = HttpClientUtils.doGet(DAILY_NOTE_URL, paramMap, headers);
            return s;
        } catch (Exception e) {
            log.info("getDailyNote error", e);
            return null;
        }
    }
}
