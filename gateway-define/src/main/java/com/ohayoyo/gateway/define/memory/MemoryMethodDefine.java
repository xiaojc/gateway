package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.MethodDefine;

import java.util.Set;

public class MemoryMethodDefine implements MethodDefine {

    //	GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    public static final MethodDefine GET = new MemoryMethodDefine().setName("GET");

    public static final MethodDefine HEAD = new MemoryMethodDefine().setName("HEAD");

    public static final MethodDefine POST = new MemoryMethodDefine().setName("POST");

    public static final MethodDefine PUT = new MemoryMethodDefine().setName("PUT");

    public static final MethodDefine PATCH = new MemoryMethodDefine().setName("PATCH");

    public static final MethodDefine DELETE = new MemoryMethodDefine().setName("DELETE");

    public static final MethodDefine OPTIONS = new MemoryMethodDefine().setName("OPTIONS");

    public static final MethodDefine TRACE = new MemoryMethodDefine().setName("TRACE");

    private String name;

    private Set<String> options;

    public MemoryMethodDefine() {
    }

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
