package com.ohayoyo.gateway.define;

public abstract class AbstractObjectDataDefine extends AbstractDataDefine implements ObjectDataDefine {

    public AbstractObjectDataDefine(String defineType) {
        super(defineType, DataScope.request, DataScope.response);
    }

}
