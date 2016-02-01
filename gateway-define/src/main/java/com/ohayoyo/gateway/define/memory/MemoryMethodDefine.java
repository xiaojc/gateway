package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.MethodDefine;

import java.util.Set;

public class MemoryMethodDefine implements MethodDefine {

    public static final MethodDefine GET = new MemoryMethodDefine().setName(GET_NAME);

    public static final MethodDefine HEAD = new MemoryMethodDefine().setName(HEAD_NAME);

    public static final MethodDefine POST = new MemoryMethodDefine().setName(POST_NAME);

    public static final MethodDefine PUT = new MemoryMethodDefine().setName(PUT_NAME);

    public static final MethodDefine PATCH = new MemoryMethodDefine().setName(PATCH_NAME);

    public static final MethodDefine DELETE = new MemoryMethodDefine().setName(DELETE_NAME);

    public static final MethodDefine OPTIONS = new MemoryMethodDefine().setName(OPTIONS_NAME);

    public static final MethodDefine TRACE = new MemoryMethodDefine().setName(TRACE_NAME);

    private String name;

    private Set<String> options;

    public String getName() {
        return name;
    }

    public MemoryMethodDefine setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getOptions() {
        return options;
    }

    public MemoryMethodDefine setOptions(Set<String> options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryMethodDefine)) return false;
        MemoryMethodDefine that = (MemoryMethodDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


}
