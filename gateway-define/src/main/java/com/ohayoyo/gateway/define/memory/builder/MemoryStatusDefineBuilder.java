package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.http.StatusDefine;
import com.ohayoyo.gateway.define.builder.ResponseDefineBuilder;
import com.ohayoyo.gateway.define.builder.StatusDefineBuilder;
import com.ohayoyo.gateway.define.memory.http.MemoryStatusDefine;

/**
 * @author 蓝明乐
 */
public class MemoryStatusDefineBuilder extends StatusDefineBuilder {

    protected MemoryStatusDefineBuilder(ResponseDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    protected StatusDefine buildDetails(Integer statusCode, String reasonPhrase) {
        return new MemoryStatusDefine()
                .setStatusCode(statusCode)
                .setReasonPhrase(reasonPhrase);
    }

}
