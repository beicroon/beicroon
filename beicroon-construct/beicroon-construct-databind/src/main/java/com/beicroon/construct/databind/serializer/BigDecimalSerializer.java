package com.beicroon.construct.databind.serializer;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

import java.math.BigDecimal;

@JacksonStdImpl
public class BigDecimalSerializer extends ToStringSerializerBase {

    public static final BigDecimalSerializer INSTANCE = new BigDecimalSerializer();

    public BigDecimalSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public final String valueToString(Object value) {
        return ((BigDecimal) value).stripTrailingZeros().toPlainString();
    }

}
