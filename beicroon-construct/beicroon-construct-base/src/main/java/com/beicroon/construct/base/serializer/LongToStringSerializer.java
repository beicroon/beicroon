package com.beicroon.construct.base.serializer;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

@JacksonStdImpl
public class LongToStringSerializer extends ToStringSerializerBase {

    public static final LongToStringSerializer INSTANCE = new LongToStringSerializer();

    public LongToStringSerializer() {
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
