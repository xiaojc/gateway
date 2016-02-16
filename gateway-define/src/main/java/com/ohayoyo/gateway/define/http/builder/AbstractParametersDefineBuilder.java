package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.FieldDefine;
import com.ohayoyo.gateway.define.ParametersDefine;
import com.ohayoyo.gateway.define.ReferenceDefine;
import com.ohayoyo.gateway.define.utils.ObjectUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class AbstractParametersDefineBuilder<Define extends ParametersDefine, Reference extends DefineBuilder> extends AbstractParentBuilder<Define, Reference> {

    private String name;

    private Set<FieldDefine> fields;

    private Map<String, ReferenceDefine> references;

    private Set<FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>>> fieldDefineBuilders = new HashSet<FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>>>();

    protected AbstractParametersDefineBuilder(Reference referenceBuilder) {
        super(referenceBuilder);
    }

    @Override
    public final Define build() {
        if (ObjectUtils.isNotEmpty(fieldDefineBuilders)) {
            for (FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>> fieldDefineBuilder : fieldDefineBuilders) {
                field(fieldDefineBuilder.build());
            }
        }
        return this.buildDetails(name, fields, references);
    }

    protected abstract Define buildDetails(String name, Set<FieldDefine> fields, Map<String, ReferenceDefine> references);

    public FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>> field() {
        FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>> fieldDefineBuilder = this.fieldBuilder();
        fieldDefineBuilders.add(fieldDefineBuilder);
        return fieldDefineBuilder;
    }

    private FieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>> fieldBuilder() {
        return new ReferenceFieldDefineBuilder<AbstractParametersDefineBuilder<Define, Reference>>(this);
    }

    public AbstractParametersDefineBuilder<Define, Reference> fields(Set<FieldDefine> fields) {
        if (ObjectUtils.isEmpty(this.fields)) {
            this.fields = new HashSet<FieldDefine>();
        }
        if (ObjectUtils.isNotEmpty(fields)) {
            this.fields.addAll(fields);
        }
        return this;
    }

    public AbstractParametersDefineBuilder<Define, Reference> field(FieldDefine field) {
        if (ObjectUtils.isEmpty(this.fields)) {
            this.fields = new HashSet<FieldDefine>();
        }
        this.fields.add(field);
        return this;
    }

    public AbstractParametersDefineBuilder<Define, Reference> name(String name) {
        this.name = name;
        return this;
    }

    public AbstractParametersDefineBuilder<Define, Reference> references(String key, ReferenceDefine referenceDefine) {
        if (ObjectUtils.isEmpty(this.references)) {
            this.references = new HashMap<String, ReferenceDefine>();
        }
        this.references.put(key, referenceDefine);
        return this;
    }

    public AbstractParametersDefineBuilder<Define, Reference> references(Map<String, ReferenceDefine> references) {
        if (ObjectUtils.isEmpty(this.references)) {
            this.references = new HashMap<String, ReferenceDefine>();
        }
        if (ObjectUtils.isNotEmpty(references)) {
            this.references.putAll(references);
        }
        return this;
    }

}
