package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.HeadersDefine;

/**
 * @author 蓝明乐
 */
public abstract class HeadersDefineBuilder<Reference extends DefineBuilder> extends AbstractParametersDefineBuilder<HeadersDefine,Reference> {

    protected HeadersDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

}
