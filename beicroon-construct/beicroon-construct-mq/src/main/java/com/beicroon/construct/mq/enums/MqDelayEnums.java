package com.beicroon.construct.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MqDelayEnums {
    ONE_SECOND(1, "延迟1秒"),
    FIVE_SECONDS(2, "延迟5秒"),
    TEN_SECONDS(3, "延迟10秒"),
    THIRTY_SECONDS(4, "延迟30秒"),
    ONE_MINUTE(5, "延迟1分钟"),
    TWO_MINUTES(6, "延迟2分钟"),
    THREE_MINUTES(7, "延迟3分钟"),
    FOUR_MINUTES(8, "延迟4分钟"),
    FIVE_MINUTES(9, "延迟5分钟"),
    SIX_MINUTES(10, "延迟6分钟"),
    SEVEN_MINUTES(11, "延迟7分钟"),
    EIGHT_MINUTES(12, "延迟8分钟"),
    NIGHT_MINUTES(13, "延迟9分钟"),
    TEN_MINUTES(14, "延迟10分钟"),
    TWENTY_MINUTES(15, "延迟20分钟"),
    THIRTY_MINUTES(16, "延迟30分钟"),
    ONE_HOUR(17, "延迟1小时"),
    TWO_HOURS(18, "延迟2小时"),
    ;

    private final int code;

    private final String name;

}
