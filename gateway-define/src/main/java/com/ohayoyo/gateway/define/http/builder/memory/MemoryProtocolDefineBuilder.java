package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.ProtocolDefine;
import com.ohayoyo.gateway.define.http.builder.ProtocolDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryProtocolDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryProtocolDefineBuilder extends ProtocolDefineBuilder {

    protected MemoryProtocolDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    @Override
    protected ProtocolDefine buildDetails(String name, Set<String> scopes) {
        return new MemoryProtocolDefine()
                .setName(name)
                .setScopes(scopes);
    }

}
