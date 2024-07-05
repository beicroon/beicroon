package com.beicroon.construct.databind.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;

@JacksonStdImpl
public class BigDecimalToFloatSerializer extends StdSerializer<BigDecimal> {

    public static final BigDecimalToFloatSerializer INSTANCE = new BigDecimalToFloatSerializer();

    public BigDecimalToFloatSerializer() {
        super(BigDecimal.class);
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeNumber(bigDecimal.floatValue());
    }

}
