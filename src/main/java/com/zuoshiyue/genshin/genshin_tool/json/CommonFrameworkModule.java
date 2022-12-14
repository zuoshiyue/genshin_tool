package com.zuoshiyue.genshin.genshin_tool.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zuoshiyue.genshin.genshin_tool.common.json.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author zuoshiyue
 * @date 2022/8/3 20:00
 * @desc
 **/
public class CommonFrameworkModule extends SimpleModule {
    public CommonFrameworkModule() {
        super("CommonFrameworkModule", Version.unknownVersion());

        // sql
        addSerializer(java.sql.Date.class, new SqlDateJsonSerializer());
        addDeserializer(java.sql.Date.class, new SqlDateJsonDeserializer());
        addSerializer(java.sql.Time.class, new SqlTimeJsonSerializer());
        addDeserializer(java.sql.Time.class, new SqlTimeJsonDeserializer());
        addSerializer(Timestamp.class, new TimestampJsonSerializer());
        addDeserializer(Timestamp.class, new TimestampJsonDeserializer());
        // 兼容jackson 2.5以下版本, 对Map.Entry序列化做特殊处理
        addSerializer(Map.Entry.class, new JsonSerializer<Map.Entry>() {
            @Override
            public void serialize(Map.Entry entry, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeObject(new KeyValue(entry.getKey(), entry.getValue()));
            }
        });
    }
}
