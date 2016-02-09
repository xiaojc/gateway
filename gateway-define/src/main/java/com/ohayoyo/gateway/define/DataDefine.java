package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Collection;

public interface DataDefine extends Serializable {

    String getDefineType();

    Collection<DataScope> getDataScopes();

}
