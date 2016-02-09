package com.ohayoyo.gateway.define;

import java.util.Collection;

public interface FormDataDefine extends DataDefine {

    Collection<FieldDataDefine> getFields();

    FormDataDefine setFields(Collection<FieldDataDefine> fields);

}
