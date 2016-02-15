package com.ohayoyo.gateway.define;

import java.util.Map;
import java.util.Set;

public abstract class AbstractParametersDefine implements ParametersDefine {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, ReferenceDefine> references;

    protected AbstractParametersDefine(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractParametersDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Set<FieldDefine> getFields() {
        return fields;
    }

    @Override
    public AbstractParametersDefine setFields(Set<FieldDefine> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public Map<String, ReferenceDefine> getReferences() {
        return references;
    }

    @Override
    public AbstractParametersDefine setReferences(Map<String, ReferenceDefine> references) {
        this.references = references;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractParametersDefine)) return false;
        AbstractParametersDefine that = (AbstractParametersDefine) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return fields != null ? fields.equals(that.fields) : that.fields == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

}
