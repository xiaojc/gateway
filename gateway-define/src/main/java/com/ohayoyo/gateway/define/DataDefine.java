package com.ohayoyo.gateway.define;

public interface DataDefine extends NameDefine, ReferenceDefine {

    @Override
    String getName();

    @Override
    DataDefine setName(String name);

}
