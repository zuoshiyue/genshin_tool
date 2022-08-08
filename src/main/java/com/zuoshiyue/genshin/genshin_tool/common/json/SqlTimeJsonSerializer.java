package com.zuoshiyue.genshin.genshin_tool.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zuoshiyue.genshin.genshin_tool.json.JacksonDateFormat;

import java.io.IOException;
import java.sql.Time;

/**
 * @author zuoshiyue
 * @date 2022/8/3 20:03
 * @desc
 **/
public class SqlTimeJsonSerializer extends JsonSerializer<Time> {

    public static final JacksonDateFormat DATE_FORMAT = new JacksonDateFormat(SqlTimeJsonDeserializer.DATE_PATTERN);

    @Override
    public void serialize(Time date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? DATE_FORMAT.format(date) : "null");
    }
}
