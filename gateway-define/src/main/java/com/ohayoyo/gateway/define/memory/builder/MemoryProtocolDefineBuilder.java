package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.http.ProtocolDefine;
import com.ohayoyo.gateway.define.builder.ProtocolDefineBuilder;
import com.ohayoyo.gateway.define.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.memory.http.MemoryProtocolDefine;

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
