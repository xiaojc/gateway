package com.ohayoyo.gateway.define;

import java.io.Serializable;

public interface EntityDefine extends Serializable {

    String getType();

    EntityDefine setType(String type);

    DataDefine getData();

    EntityDefine setData(DataDefine data);

}
