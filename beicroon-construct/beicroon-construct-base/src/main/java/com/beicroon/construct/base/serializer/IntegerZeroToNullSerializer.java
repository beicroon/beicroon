package com.beicroon.construct.base.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@JacksonStdImpl
public class IntegerZeroToNullSerializer extends StdSerializer<Integer> {

    public static final IntegerZeroToNullSerializer INSTANCE = new IntegerZeroToNullSerializer();

    public IntegerZeroToNullSerializer() {
        super(Integer.class);
    }

    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (Integer.valueOf(0).equals(value)) {
            gen.writeNull();

            return;
        }

        gen.writeNumber(value);
    }

}
