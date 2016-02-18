package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.define.http.RequestDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class InterfaceDefineBuilder implements DefineBuilder<InterfaceDefine> {

    private String key;

    private String description;

    private RequestDefine request;

    private ResponseDefine response;

    private RequestDefineBuilder requestDefineBuilder;

    private ResponseDefineBuilder responseDefineBuilder;

    public InterfaceDefineBuilder key(String key) {
        this.key = key;
        return this;
    }

    public InterfaceDefineBuilder description(String description) {
        this.description = description;
        return this;
    }

    public InterfaceDefineBuilder request(RequestDefine request) {
        this.request = request;
        return this;
    }

    public InterfaceDefineBuilder response(ResponseDefine response) {
        this.response = response;
        return this;
    }

    public RequestDefineBuilder request() {
        requestDefineBuilder = this.requestBuilder();
        return requestDefineBuilder;
    }

    public ResponseDefineBuilder response() {
        responseDefineBuilder = this.responseBuilder();
        return responseDefineBuilder;
    }

    @Override
    public final InterfaceDefine build() {
        if (!ObjectUtils.isEmpty(requestDefineBuilder)) {
            this.request(requestDefineBuilder.build());
        }
        if (!ObjectUtils.isEmpty(responseDefineBuilder)) {
            this.response(responseDefineBuilder.build());
        }
        return this.buildDetails(key, description, request, response);
    }

    protected abstract RequestDefineBuilder requestBuilder();

    protected abstract ResponseDefineBuilder responseBuilder();

    protected abstract InterfaceDefine buildDetails(String key, String description, RequestDefine request, ResponseDefine response);

}
