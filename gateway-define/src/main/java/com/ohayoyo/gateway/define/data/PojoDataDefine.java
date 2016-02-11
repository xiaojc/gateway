package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.pojo.PojoDefine;
import com.ohayoyo.gateway.define.pojo.FieldDefine;
import com.ohayoyo.gateway.define.TypeDefine;

import java.util.Map;
import java.util.Set;

public class PojoDataDefine extends AbstractDataDefine implements DataDefine, PojoDefine {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, PojoDefine> references;

    public PojoDataDefine() {
        super(TypeDefine.POJO);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PojoDataDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Set<FieldDefine> getFields() {
        return fields;
    }

    @Override
    public PojoDataDefine setFields(Set<FieldDefine> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public Map<String, PojoDefine> getReferences() {
        return references;
    }

    @Override
    public PojoDataDefine setReferences(Map<String, PojoDefine> references) {
        this.references = references;
        return this;
    }

}
