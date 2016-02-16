package com.ohayoyo.gateway.define.http.builder.memory;

import com.ohayoyo.gateway.define.http.StatusDefine;
import com.ohayoyo.gateway.define.http.builder.ResponseDefineBuilder;
import com.ohayoyo.gateway.define.http.builder.StatusDefineBuilder;
import com.ohayoyo.gateway.define.http.memory.MemoryStatusDefine;

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
