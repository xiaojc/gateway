package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.*;

import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public class ClassDataDefine extends AbstractDataDefine implements DataDefine, ReferenceFieldsDefine {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, ReferenceDefine> references;

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
    public Map<String, ReferenceDefine> getReferences() {
        return references;
    }

    @Override
    public ClassDataDefine setReferences(Map<String, ReferenceDefine> references) {
        this.references = references;
        return this;
    }

}
