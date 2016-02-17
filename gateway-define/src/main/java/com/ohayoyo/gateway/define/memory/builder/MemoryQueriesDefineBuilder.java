package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.QueriesDefineBuilder;
import com.ohayoyo.gateway.define.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.ParameterDefine;
import com.ohayoyo.gateway.define.core.QueriesDefine;
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
    protected QueriesDefine buildDetails(Set<ParameterDefine> fields) {
        return (QueriesDefine) new MemoryQueriesDefine()
                .setFields(fields);
    }

}
