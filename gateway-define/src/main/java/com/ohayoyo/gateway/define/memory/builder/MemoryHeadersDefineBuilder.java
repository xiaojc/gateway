package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.DefineBuilder;
import com.ohayoyo.gateway.define.builder.HeadersDefineBuilder;
import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.core.Parameter;
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
    protected HeadersDefine buildDetails(Set<Parameter> parameters) {
        return (HeadersDefine) new MemoryHeadersDefine()
                .setParameters(parameters);
    }

}
