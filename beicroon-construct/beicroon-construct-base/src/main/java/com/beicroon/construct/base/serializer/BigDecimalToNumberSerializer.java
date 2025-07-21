package com.beicroon.construct.base.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;

@JacksonStdImpl
public class BigDecimalToNumberSerializer extends StdSerializer<BigDecimal> {

    public static final BigDecimalToNumberSerializer INSTANCE = new BigDecimalToNumberSerializer();

    public BigDecimalToNumberSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeNumber(value.floatValue());
    }

}
