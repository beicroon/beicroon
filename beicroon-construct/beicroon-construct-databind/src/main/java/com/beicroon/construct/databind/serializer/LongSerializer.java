package com.beicroon.construct.databind.serializer;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

@JacksonStdImpl
public class LongSerializer extends ToStringSerializerBase {

    public static final LongSerializer INSTANCE = new LongSerializer();

    public LongSerializer() {
        super(Long.class);
    }

    @Override
    public final String valueToString(Object value) {
        Long l = (Long) value;

        if (Long.valueOf(0).equals(l)) {
            return null;
        }

        return String.valueOf(l);
    }

}
