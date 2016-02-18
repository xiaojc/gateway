package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.QueriesDefineBuilder;
import com.ohayoyo.gateway.define.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.core.Parameter;
import com.ohayoyo.gateway.define.http.QueriesDefine;
import com.ohayoyo.gateway.define.memory.http.MemoryQueriesDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryQueriesDefineBuilder extends QueriesDefineBuilder {

    protected MemoryQueriesDefineBuilder(RequestDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected QueriesDefine buildDetails(Set<Parameter> parameters) {
        return (QueriesDefine) new MemoryQueriesDefine()
                .setParameters(parameters);
    }

}
