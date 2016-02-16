package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.builder.memory.MemoryInterfaceDefineBuilder;

/**
 * @author 蓝明乐
 */
public final class Builders {

    public static InterfaceDefineBuilder memory() {
        return new MemoryInterfaceDefineBuilder();
    }

}
