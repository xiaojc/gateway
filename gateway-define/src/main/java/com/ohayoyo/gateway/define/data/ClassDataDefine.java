package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.*;

import java.util.Map;
import java.util.Set;

public class ClassDataDefine extends AbstractDataDefine implements DataDefine, ReferenceDefine<ClassDataDefine> {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, ClassDataDefine> references;

    public ClassDataDefine() {
        super(DataTypeDefine.DATA_TYPE_CLASS);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ClassDataDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Set<FieldDefine> getFields() {
        return fields;
    }

    @Override
    public ClassDataDefine setFields(Set<FieldDefine> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public Map<String, ClassDataDefine> getReferences() {
        return references;
    }

    @Override
    public ClassDataDefine setReferences(Map<String, ClassDataDefine> references) {
        this.references = references;
        return this;
    }

}
