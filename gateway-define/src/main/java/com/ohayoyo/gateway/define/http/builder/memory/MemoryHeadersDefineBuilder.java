package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.http.builder.DefineBuilder;
import com.ohayoyo.gateway.define.http.builder.HeadersDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryHeadersDefine;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryHeadersDefineBuilder<Reference extends DefineBuilder> extends HeadersDefineBuilder<Reference> {

    protected MemoryHeadersDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected HeadersDefine buildDetails(String name, Set fields, Map references) {
        return (HeadersDefine) new MemoryHeadersDefine()
                .setName(name)
                .setFields(fields)
                .setReferences(references);
    }

}
