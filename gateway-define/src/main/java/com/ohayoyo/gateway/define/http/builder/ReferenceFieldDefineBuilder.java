package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.FieldDefine;
import com.ohayoyo.gateway.define.ReferenceFieldDefine;

/**
 * @author 蓝明乐
 */
public class ReferenceFieldDefineBuilder<Reference extends DefineBuilder> extends FieldDefineBuilder<Reference> {

    protected ReferenceFieldDefineBuilder(Reference beanBuilder) {
        super(beanBuilder);
    }

    @Override
    protected FieldDefine buildDetails(String name, String comment, String overview, String description, String dataType, Integer dataLength, Boolean nullable, Object defaultValue) {
        return new ReferenceFieldDefine()
                .setName(name)
                .setComment(comment)
                .setDataLength(dataLength)
                .setDataType(dataType)
                .setDefaultValue(defaultValue)
                .setDescription(description)
                .setNullable(nullable)
                .setOverview(overview);
    }

}
