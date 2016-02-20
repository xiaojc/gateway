package com.ohayoyo.gateway.memory.resolver;

import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;
import com.ohayoyo.gateway.memory.core.MemoryGatewayType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemoryGatewayTypeResolver implements GatewayTypeResolver<MemoryGatewayType> {

    @Override
    public Class<?> resolve(MemoryGatewayType type) {
        if (!ObjectUtils.isEmpty(type)) {
            String name = type.getName();
            if (!StringUtils.isEmpty(name)) {
                if (BYTE.equalsIgnoreCase(name)) {
                    return byte.class;
                } else if (SHORT.equalsIgnoreCase(name)) {
                    return short.class;
                } else if (INT.equalsIgnoreCase(name)) {
                    return int.class;
                } else if (LONG.equalsIgnoreCase(name)) {
                    return long.class;
                } else if (CHAR.equalsIgnoreCase(name)) {
                    return char.class;
                } else if (FLOAT.equalsIgnoreCase(name)) {
                    return float.class;
                } else if (DOUBLE.equalsIgnoreCase(name)) {
                    return double.class;
                } else if (BOOLEAN.equalsIgnoreCase(name)) {
                    return boolean.class;
                } else if (LIST.equalsIgnoreCase(name)) {
                    return List.class;
                } else if (SET.equalsIgnoreCase(name)) {
                    return Set.class;
                } else if (MAP.equalsIgnoreCase(name)) {
                    return Map.class;
                } else if (STRING.equalsIgnoreCase(name)) {
                    return String.class;
                } else if (FILE.equalsIgnoreCase(name)) {
                    return File.class;
                }
            }
        }
        return Object.class;
    }

}
