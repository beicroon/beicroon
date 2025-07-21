package com.beicroon.construct.utils;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.enums.DateTimeEnums;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

@Slf4j
public final class TimeUtils {

    private final static LocalTime MIN = LocalTime.of(0, 0, 0);

    private final static LocalTime MAX = LocalTime.of(23, 59, 59);

    private TimeUtils() {

    }

    public static Long currentTimestamp() {
        return TimeUtils.currentTimeMillis() / 1000;
    }

    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now().withNano(0);
    }

    public static LocalDateTime startOfDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return LocalDateTime.of(date, TimeUtils.MIN);
    }

    public static LocalDateTime endOfDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return LocalDateTime.of(date, TimeUtils.MAX);
    }

    public static LocalDateTime toDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(SystemConstant.TIMEZONE).toLocalDateTime();
    }

    public static LocalDate toDate(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(SystemConstant.TIMEZONE).toLocalDate();
    }

    public static LocalDate toDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return date.toLocalDate();
    }

    public static LocalTime toTime(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(SystemConstant.TIMEZONE).toLocalTime();
    }

    public static LocalTime toTime(LocalDateTime date) {
        if (date == null) {
            return null;
        }

        return date.toLocalTime();
    }

    public static LocalDateTime toDateTime(String time, DateTimeFormatter formatter) {
        if (EmptyUtils.isEmpty(time)) {
            return null;
        }

        return LocalDateTime.parse(time, formatter);
    }

    public static LocalDate toDate(String time, DateTimeFormatter formatter) {
        if (EmptyUtils.isEmpty(time)) {
            return null;
        }

        return LocalDate.parse(time, formatter);
    }

    public static LocalTime toTime(String time, DateTimeFormatter formatter) {
        if (EmptyUtils.isEmpty(time)) {
            return null;
        }

        return LocalTime.parse(time, formatter);
    }

    public static String format(LocalDateTime time) {
        if (time == null) {
            return EmptyUtils.emptyString();
        }

        return time.format(DateTimeEnums.Y_M_D_H_M_S.getFormatter());
    }

    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        if (time == null) {
            return null;
        }

        return time.format(formatter);
    }

    public static String format(LocalDate time) {
        if (time == null) {
            return null;
        }

        return time.format(DateTimeEnums.Y_M_D.getFormatter());
    }

    public static String format(LocalDate time, DateTimeFormatter formatter) {
        if (time == null) {
            return null;
        }

        return time.format(formatter);
    }

    public static String format(LocalTime time) {
        if (time == null) {
            return null;
        }

        return time.format(DateTimeEnums.H_M_S.getFormatter());
    }

    public static String format(LocalTime time, DateTimeFormatter formatter) {
        if (time == null) {
            return null;
        }

        return time.format(formatter);
    }

    public static BigDecimal toDays(Temporal start, Temporal end) {
        return NumberUtils.divide(TimeUtils.toHours(start, end), BigDecimal.valueOf(24.0));
    }

    public static BigDecimal toHours(Temporal start, Temporal end) {
        return NumberUtils.divide(TimeUtils.toMinutes(start, end), BigDecimal.valueOf(60.0));
    }

    public static BigDecimal toMinutes(Temporal start, Temporal end) {
        return NumberUtils.divide(TimeUtils.toSeconds(start, end), BigDecimal.valueOf(60.0));
    }

    public static Long toSeconds(Temporal start, Temporal end) {
        return NumberUtils.divide(Duration.between(start, end).toMillis(), 1000L);
    }

    public static LocalTime parseTime(String time) {
        return TimeUtils.parseTime(time, DateTimeEnums.H_M_S.getFormatter());
    }

    public static LocalTime parseTime(String time, DateTimeFormatter formatter) {
        return EmptyUtils.isEmpty(time) ? null : LocalTime.parse(time, formatter);
    }

    public static LocalDate parseDate(String time) {
        return TimeUtils.parseDate(time, DateTimeEnums.Y_M_D.getFormatter());
    }

    public static LocalDate parseDate(String time, DateTimeFormatter formatter) {
        return EmptyUtils.isEmpty(time) ? null : LocalDate.parse(time, formatter);
    }

    public static LocalDateTime parseDateTime(String time) {
        return TimeUtils.parseDateTime(time, DateTimeEnums.Y_M_D_H_M_S.getFormatter());
    }

    public static LocalDateTime parseDateTime(String time, DateTimeFormatter formatter) {
        return EmptyUtils.isEmpty(time) ? null : LocalDateTime.parse(time, formatter);
    }

    public static boolean lt(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return start.isBefore(end);
    }

    public static boolean lte(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return start.isBefore(end) || start.isEqual(end);
    }

    public static boolean gt(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return start.isAfter(end);
    }

    public static boolean gte(@NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return start.isAfter(end) || start.isEqual(end);
    }

}
