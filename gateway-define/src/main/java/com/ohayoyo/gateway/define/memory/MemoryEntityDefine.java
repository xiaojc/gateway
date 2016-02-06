package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.EntityDefine;

public class MemoryEntityDefine implements EntityDefine {

    private String type;

    private DataDefine data;

    public String getType() {
        return type;
    }

    public MemoryEntityDefine setType(String type) {
        this.type = type;
        return this;
    }

    public DataDefine getData() {
        return data;
    }

    public MemoryEntityDefine setData(DataDefine data) {
        this.data = data;
        return this;
    }

}
