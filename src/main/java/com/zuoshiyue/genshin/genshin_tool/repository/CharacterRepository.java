package com.zuoshiyue.genshin.genshin_tool.repository;


import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.CharacterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.zuoshiyue.genshin.genshin_tool.common.account.AccountConfig.SERVER;

/**
 * @author lupengfei
 */
@Slf4j
@Component
public class CharacterRepository extends BaseRepository {

    private static final String DAILY_NOTE_URL = "https://api-takumi-record.mihoyo.com/game_record/app/genshin/api/character?server=%s&role_id=%s";

    public CharacterResponse getCharacter(String roleId, String cookie) {

        try {
            String fullUrl = String.format(DAILY_NOTE_URL, SERVER, roleId);
            String httpResponse = getHttpResponse(fullUrl, roleId, cookie);
            Map<String, Object> stringObjectMap = JsonUtil.ofMap(httpResponse, String.class, Object.class);
            if (stringObjectMap.get("retcode") == null || (int) stringObjectMap.get("retcode") != 0) {
                return null;
            }
            Object data = stringObjectMap.get("data");
            return JsonUtil.of(JsonUtil.toJson(data), CharacterResponse.class);
        } catch (Exception e) {
            log.info("getDailyNote error", e);
            return null;
        }
    }

}
