package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.DefineBuilder;
import com.ohayoyo.gateway.define.builder.HeadersDefineBuilder;
import com.ohayoyo.gateway.define.core.HeadersDefine;
import com.ohayoyo.gateway.define.ParameterDefine;
import com.ohayoyo.gateway.define.memory.http.MemoryHeadersDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryHeadersDefineBuilder<Reference extends DefineBuilder> extends HeadersDefineBuilder<Reference> {

    protected MemoryHeadersDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected HeadersDefine buildDetails(Set<ParameterDefine> fields) {
        return (HeadersDefine) new MemoryHeadersDefine()
                .setFields(fields);
    }

}
