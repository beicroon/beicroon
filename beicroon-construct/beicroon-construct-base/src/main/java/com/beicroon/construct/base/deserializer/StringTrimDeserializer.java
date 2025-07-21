package com.beicroon.construct.base.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringTrimDeserializer extends JsonDeserializer<String> {

    public static final StringTrimDeserializer INSTANCE = new StringTrimDeserializer();

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getValueAsString();

        if (value == null) {
            return null;
        }

        return value.trim();
    }

}
