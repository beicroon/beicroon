package com.beicroon.starter.auth.utils;


import com.beicroon.construct.auth.entity.AuthThread;
import com.beicroon.construct.auth.utils.AuthUtils;

import java.util.Collection;
import java.util.function.Consumer;

public final class AsyncUtils {

    private AsyncUtils() {

    }

    public static <T> void foreach(Collection<T> collection, Consumer<T> consumer) {
        AuthThread threadInfo = AuthUtils.getAuthThreadInfo();

        collection.parallelStream().forEach(item -> {
            AuthUtils.clear();

            AuthUtils.setAuthThreadInfo(threadInfo);

            consumer.accept(item);
        });
    }

}
