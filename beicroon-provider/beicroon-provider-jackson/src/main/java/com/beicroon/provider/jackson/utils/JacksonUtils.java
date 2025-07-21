package com.beicroon.provider.jackson.utils;

import com.beicroon.construct.base.deserializer.StringTrimDeserializer;
import com.beicroon.construct.base.serializer.BigDecimalToStringSerializer;
import com.beicroon.construct.base.serializer.IntegerZeroToNullSerializer;
import com.beicroon.construct.base.serializer.LongToStringSerializer;
import com.beicroon.construct.enums.DateTimeEnums;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public final class JacksonUtils {

    private JacksonUtils() {

    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        mapper.registerModule(JacksonUtils.getSimpleModule());
        mapper.registerModule(JacksonUtils.getJavaTimeModule());

        return mapper;
    }

    public static SimpleModule getSimpleModule() {
        SimpleModule module = new SimpleModule();

        module.addSerializer(Long.class, LongToStringSerializer.INSTANCE);
        module.addSerializer(Integer.class, IntegerZeroToNullSerializer.INSTANCE);
        module.addSerializer(BigDecimal.class, BigDecimalToStringSerializer.INSTANCE);

        module.addDeserializer(String.class, StringTrimDeserializer.INSTANCE);

        return module;
    }

    public static JavaTimeModule getJavaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();

        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeEnums.Y_M_D_H_M_S.getFormatter()));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeEnums.Y_M_D_H_M_S.getFormatter()));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeEnums.Y_M_D.getFormatter()));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeEnums.Y_M_D.getFormatter()));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeEnums.H_M_S.getFormatter()));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeEnums.H_M_S.getFormatter()));

        return module;
    }

}
