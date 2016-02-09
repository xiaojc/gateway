package com.ohayoyo.gateway.define;

public class DefaultStreamDataDefine extends AbstractDataDefine implements StreamDataDefine {

    public DefaultStreamDataDefine() {
        super(StreamDataDefine.DEFINE_TYPE_APPLICATION_OCTET_STREAM_VALUE, DataScope.request, DataScope.response);
    }

}
