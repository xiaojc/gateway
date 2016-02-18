package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.http.EntityDefine;
import com.ohayoyo.gateway.define.http.HeadersDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;
import com.ohayoyo.gateway.define.http.StatusDefine;
import com.ohayoyo.gateway.define.builder.*;
import com.ohayoyo.gateway.define.memory.http.MemoryResponseDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryResponseDefineBuilder extends ResponseDefineBuilder {

    protected MemoryResponseDefineBuilder(InterfaceDefineBuilder interfaceDefineBuilder) {
        super(interfaceDefineBuilder);
    }

    @Override
    protected ResponseDefine buildDetails(Set<StatusDefine> statuses, HeadersDefine headers, EntityDefine entity) {
        return new MemoryResponseDefine()
                .setStatuses(statuses)
                .setHeaders(headers)
                .setEntity(entity);
    }

    @Override
    protected EntityDefineBuilder<ResponseDefineBuilder> entityBuilder() {
        return new MemoryEntityDefineBuilder<ResponseDefineBuilder>(this);
    }

    @Override
    protected HeadersDefineBuilder<ResponseDefineBuilder> headersBuilder() {
        return new MemoryHeadersDefineBuilder<ResponseDefineBuilder>(this);
    }

    @Override
    protected StatusDefineBuilder statusBuilder() {
        return new MemoryStatusDefineBuilder(this);
    }

}
