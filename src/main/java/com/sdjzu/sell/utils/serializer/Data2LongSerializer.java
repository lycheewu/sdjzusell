package com.sdjzu.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 *
 *
 * @author lychee
 * @date 2020/1/13 21:35
 */
public class Data2LongSerializer extends JsonSerializer<Date> {

    @Override
    //转换时间 将毫秒级转换成秒级
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getTime() / 1000);
    }
}
