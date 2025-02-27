package com.beicroon.construct.utils;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtils {

    private final static String UUID_REPLACE = "-";

    private RandomUtils() {

    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace(UUID_REPLACE, EmptyUtils.emptyString());
    }

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static String nextIntString(int min, int max, int length) {
        return String.format("%0" + length + "d", RandomUtils.nextInt(min, max));
    }

    public static long nextLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    public static long nextLong(long bound) {
        return ThreadLocalRandom.current().nextLong(bound);
    }

    public static long nextLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String nextString(int length) {
        SecureRandom random = new SecureRandom();

        byte[] bytes = new byte[length];

        random.nextBytes(bytes);

        return HashUtils.base64Encode(bytes);
    }

}
