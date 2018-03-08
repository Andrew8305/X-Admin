package com.leaf.xadmin.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 用于加密字符串
 *
 * @author leaf
 * <p>date: 2018-03-06 13:53</p>
 * <p>version: 1.0</p>
 */
public class String2SecretSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(secretString(s, 3, 3, '*'));
    }

    private String secretString(String target, int start, int end, char c) {
        char[] chars = target.toCharArray();
        for (int i = 0, length = chars.length; i < length; i++) {
            if (i >= start && i <= length - (end + 1)) {
                chars[i] = c;
            }
        }
        return String.valueOf(chars);
    }
}
