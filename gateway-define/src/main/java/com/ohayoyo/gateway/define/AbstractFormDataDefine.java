package com.ohayoyo.gateway.define;

import java.util.Collection;

public abstract class AbstractFormDataDefine extends AbstractDataDefine implements FormDataDefine {

    private Collection<FieldDataDefine> fields;

    public AbstractFormDataDefine(String defineType) {
        super(defineType, DataScope.request);
    }

    @Override
    public Collection<FieldDataDefine> getFields() {
        return fields;
    }

    @Override
    public AbstractFormDataDefine setFields(Collection<FieldDataDefine> fields) {
        this.fields = fields;
        return this;
    }

}
