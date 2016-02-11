package com.ohayoyo.gateway.define.pojo;

import java.util.Map;
import java.util.Set;

public class DefaultPojoDefine implements PojoDefine {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, PojoDefine> references;

    public DefaultPojoDefine() {
    }

    public DefaultPojoDefine(Set<FieldDefine> fields) {
        this.fields = fields;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DefaultPojoDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Set<FieldDefine> getFields() {
        return fields;
    }

    @Override
    public DefaultPojoDefine setFields(Set<FieldDefine> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public Map<String, PojoDefine> getReferences() {
        return references;
    }

    @Override
    public DefaultPojoDefine setReferences(Map<String, PojoDefine> references) {
        this.references = references;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultPojoDefine)) return false;
        DefaultPojoDefine that = (DefaultPojoDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
