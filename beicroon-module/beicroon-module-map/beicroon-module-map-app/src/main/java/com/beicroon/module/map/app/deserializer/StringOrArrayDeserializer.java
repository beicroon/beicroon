package com.beicroon.module.map.app.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringOrArrayDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.currentToken().equals(JsonToken.START_ARRAY)) {
            return String.join(",", jsonParser.readValueAs(String[].class));
        } else if (jsonParser.currentToken().equals(JsonToken.VALUE_STRING)) {
            return jsonParser.getValueAsString();
        } else {
            return null;
        }
    }

}

