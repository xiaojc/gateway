package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.http.QueriesDefine;

/**
 * @author 蓝明乐
 */
public abstract class QueriesDefineBuilder extends AbstractParametersDefineBuilder<QueriesDefine, RequestDefineBuilder> {

    protected QueriesDefineBuilder(RequestDefineBuilder referenceBuilder) {
        super(referenceBuilder);
    }

}
