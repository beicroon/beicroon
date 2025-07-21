package com.beicroon.construct.utils;

import java.time.LocalDateTime;

public class SequenceUtils {

    private SequenceUtils() {

    }

    public static String getDatetimeCode(String prefix) {
        return SequenceUtils.getDatetimeCode(prefix, null);
    }

    public static String getDatetimeCode(String prefix, String suffix) {
        StringBuilder code = new StringBuilder(prefix);

        LocalDateTime datetime = TimeUtils.now();

        code.append(String.valueOf(datetime.getYear()).substring(2));
        code.append(String.format("%02d", datetime.getMonthValue()));
        code.append(String.format("%02d", datetime.getDayOfMonth()));
        code.append(String.format("%02d", datetime.getHour()));
        code.append(String.format("%02d", datetime.getMinute()));
        code.append(String.format("%02d", datetime.getSecond()));

        if (suffix != null) {
            code.append(suffix);
        }

        return code.toString();
    }

}
