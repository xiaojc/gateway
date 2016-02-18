package com.ohayoyo.gateway.define;

import com.ohayoyo.gateway.define.builder.InterfaceDefineBuilder;
import com.ohayoyo.gateway.define.memory.builder.MemoryInterfaceDefineBuilder;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public final class Builders {

    public static final Map<String, Class<? extends InterfaceDefineBuilder>> REGISTERS = new HashMap<String, Class<? extends InterfaceDefineBuilder>>();

    private static final String MEMORY = "memory";

    static {
        REGISTERS.put(MEMORY, MemoryInterfaceDefineBuilder.class);
    }

    public static InterfaceDefineBuilder builder(String name) throws IllegalAccessException, InstantiationException {
        if (!StringUtils.isEmpty(name)) {
            if (REGISTERS.containsKey(name)) {
                Class<? extends InterfaceDefineBuilder> InterfaceDefineBuilderClass = REGISTERS.get(name);
                return InterfaceDefineBuilderClass.newInstance();
            }
        }
        return null;
    }

    public static InterfaceDefineBuilder memory() {
        InterfaceDefineBuilder interfaceDefineBuilder = null;
        try {
            interfaceDefineBuilder = builder(MEMORY);
        } catch (Exception e) {
            //none
        }
        return interfaceDefineBuilder;
    }

}
