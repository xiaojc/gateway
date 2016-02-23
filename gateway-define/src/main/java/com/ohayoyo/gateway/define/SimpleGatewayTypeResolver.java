package com.ohayoyo.gateway.define;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleGatewayTypeResolver implements GatewayTypeResolver {

    @Override
    public Class<?> resolve(String type) {
        if (!ObjectUtils.isEmpty(type)) {
            if (!StringUtils.isEmpty(type)) {
                if (BYTE.equalsIgnoreCase(type)) {
                    return byte.class;
                } else if (SHORT.equalsIgnoreCase(type)) {
                    return short.class;
                } else if (INT.equalsIgnoreCase(type)) {
                    return int.class;
                } else if (LONG.equalsIgnoreCase(type)) {
                    return long.class;
                } else if (CHAR.equalsIgnoreCase(type)) {
                    return char.class;
                } else if (FLOAT.equalsIgnoreCase(type)) {
                    return float.class;
                } else if (DOUBLE.equalsIgnoreCase(type)) {
                    return double.class;
                } else if (BOOLEAN.equalsIgnoreCase(type)) {
                    return boolean.class;
                } else if (LIST.equalsIgnoreCase(type)) {
                    return List.class;
                } else if (SET.equalsIgnoreCase(type)) {
                    return Set.class;
                } else if (MAP.equalsIgnoreCase(type)) {
                    return Map.class;
                } else if (STRING.equalsIgnoreCase(type)) {
                    return String.class;
                } else if (FILE.equalsIgnoreCase(type)) {
                    return File.class;
                }
            }
        }
        return Object.class;
    }

}
