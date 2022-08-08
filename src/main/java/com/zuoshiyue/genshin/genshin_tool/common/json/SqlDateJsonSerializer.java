package com.zuoshiyue.genshin.genshin_tool.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zuoshiyue.genshin.genshin_tool.json.JacksonDateFormat;

import java.io.IOException;
import java.sql.Date;

/**
 * @author zuoshiyue
 * @date 2022/8/3 20:03
 * @desc
 **/
public class SqlDateJsonSerializer extends JsonSerializer<Date> {

    public static final JacksonDateFormat DATE_FORMAT = new JacksonDateFormat(SqlDateJsonDeserializer.DATE_PATTERN);

    @Override
    public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? DATE_FORMAT.format(date) : "null");
    }
}
