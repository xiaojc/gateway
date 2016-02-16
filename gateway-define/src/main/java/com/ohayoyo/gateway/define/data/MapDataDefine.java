package com.ohayoyo.gateway.define.data;

import com.ohayoyo.gateway.define.AbstractDataDefine;
import com.ohayoyo.gateway.define.DataDefine;
import com.ohayoyo.gateway.define.DataTypeDefine;

/**
 * @author 蓝明乐
 */
public class MapDataDefine extends AbstractDataDefine {

    private DataDefine key;

    private DataDefine value;

    public MapDataDefine() {
        super(DataTypeDefine.DATA_TYPE_MAP);
    }

    public DataDefine getKey() {
        return key;
    }

    public MapDataDefine setKey(DataDefine key) {
        this.key = key;
        return this;
    }

    public DataDefine getValue() {
        return value;
    }

    public MapDataDefine setValue(DataDefine value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapDataDefine)) return false;
        if (!super.equals(o)) return false;
        MapDataDefine that = (MapDataDefine) o;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}
