package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.EntityDefine;

public class MemoryEntityDefine implements EntityDefine {

    private String type;

    private Object data;

    public MemoryEntityDefine() {
    }

    public String getType() {
        return type;
    }

    public MemoryEntityDefine setType(String type) {
        this.type = type;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MemoryEntityDefine setData(Object data) {
        this.data = data;
        return this;
    }

}
