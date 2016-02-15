package com.ohayoyo.gateway.define.http.builder;

import java.util.Collection;

public final class BuilderUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return !isNotEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }

}
