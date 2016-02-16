package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.StatusDefine;

/**
 * @author 蓝明乐
 */
public abstract class StatusDefineBuilder extends AbstractParentBuilder<StatusDefine, ResponseDefineBuilder> {

    private Integer statusCode;

    private String reasonPhrase;

    protected StatusDefineBuilder(ResponseDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

    public StatusDefineBuilder statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public StatusDefineBuilder reasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    @Override
    public final StatusDefine build() {
        return this.buildDetails(statusCode, reasonPhrase);
    }

    protected abstract StatusDefine buildDetails(Integer statusCode, String reasonPhrase);

}
