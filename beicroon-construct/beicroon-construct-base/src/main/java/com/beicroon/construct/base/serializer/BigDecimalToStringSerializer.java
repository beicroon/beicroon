package com.beicroon.construct.base.serializer;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;

import java.math.BigDecimal;

@JacksonStdImpl
public class BigDecimalToStringSerializer extends ToStringSerializerBase {

    public static final BigDecimalToStringSerializer INSTANCE = new BigDecimalToStringSerializer();

    public BigDecimalToStringSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public final String valueToString(Object value) {
        BigDecimal bigDecimal = ((BigDecimal) value);

        if (BigDecimal.ZERO.compareTo(bigDecimal) == 0) {
            return null;
        }

        return ((BigDecimal) value).stripTrailingZeros().toPlainString();
    }

}
