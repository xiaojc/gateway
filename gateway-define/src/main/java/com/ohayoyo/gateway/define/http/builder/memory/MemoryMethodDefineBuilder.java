package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.MethodDefine;
import com.ohayoyo.gateway.define.http.builder.MethodDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryMethodDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryMethodDefineBuilder extends MethodDefineBuilder {

    protected MemoryMethodDefineBuilder(RequestDefineBuilder requestDefineBuilder) {
        super(requestDefineBuilder);
    }

    @Override
    protected MethodDefine buildDetails(String name, Set<String> scopes) {
        return new MemoryMethodDefine()
                .setName(name)
                .setScopes(scopes);
    }

}
