package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public abstract class AbstractDataDefine implements DataDefine {

    private String name;

    protected AbstractDataDefine(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractDataDefine setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDataDefine)) return false;
        AbstractDataDefine that = (AbstractDataDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
