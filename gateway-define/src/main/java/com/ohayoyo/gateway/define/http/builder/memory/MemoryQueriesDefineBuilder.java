package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.FieldDefine;
import com.ohayoyo.gateway.define.ReferenceDefine;
import com.ohayoyo.gateway.define.http.QueriesDefine;
import com.ohayoyo.gateway.define.http.builder.QueriesDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryQueriesDefine;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryQueriesDefineBuilder extends QueriesDefineBuilder {

    protected MemoryQueriesDefineBuilder(RequestDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected QueriesDefine buildDetails(String name, Set<FieldDefine> fields, Map<String, ReferenceDefine> references) {
        return (QueriesDefine) new MemoryQueriesDefine()
                .setName(name)
                .setFields(fields)
                .setReferences(references);
    }

}
