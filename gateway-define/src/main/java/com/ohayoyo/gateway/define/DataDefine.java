package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public interface DataDefine extends NameDefine, ReferenceDefine {

    @Override
    String getName();

    @Override
    DataDefine setName(String name);

}
