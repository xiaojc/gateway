package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.InterfaceDefineBuilder;
import com.ohayoyo.gateway.define.builder.RequestDefineBuilder;
import com.ohayoyo.gateway.define.builder.ResponseDefineBuilder;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.define.http.RequestDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;
import com.ohayoyo.gateway.define.memory.http.MemoryInterfaceDefine;

/**
 * @author 蓝明乐
 */
public class MemoryInterfaceDefineBuilder extends InterfaceDefineBuilder {

    @Override
    protected RequestDefineBuilder requestBuilder() {
        return new MemoryRequestDefineBuilder(this);
    }

    @Override
    protected ResponseDefineBuilder responseBuilder() {
        return new MemoryResponseDefineBuilder(this);
    }

    @Override
    protected InterfaceDefine buildDetails(String key, String description, RequestDefine request, ResponseDefine response) {
        return new MemoryInterfaceDefine()
                .setKey(key)
                .setDescription(description)
                .setRequest(request)
                .setResponse(response);
    }

}
