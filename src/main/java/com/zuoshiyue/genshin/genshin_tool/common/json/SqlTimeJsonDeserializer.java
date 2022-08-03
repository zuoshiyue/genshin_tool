package com.zuoshiyue.genshin.genshin_tool.common.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zuoshiyue.genshin.genshin_tool.json.JacksonDateFormat;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;

/**
 * @author lupengfei
 * @date 2022/8/3 20:03
 * @desc
 **/
public class SqlTimeJsonDeserializer extends JsonDeserializer<Time> {

    public static final String DATE_PATTERN = JacksonDateFormat.PATTERN_HHMMSS;

    @Override
    public Time deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String date = jp.getText();
        if (date != null && !date.isEmpty()) {
            try {
                java.util.Date utilDate = JacksonDateFormat.parseDateStrictly(date, DATE_PATTERN);
                return new Time(utilDate.getTime());
            }
            catch (ParseException e) {
                throw new JsonParseException(jp, "cannot parse date string: " + date, jp.getCurrentLocation(), e);
            }
        }
        return null;
    }
}
