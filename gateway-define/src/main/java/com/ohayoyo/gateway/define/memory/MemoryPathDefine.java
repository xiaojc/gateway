package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.PathDefine;
import com.ohayoyo.gateway.define.PathVariableDefine;

import java.util.Set;

public class MemoryPathDefine implements PathDefine {

    private String project;

    private String module;

    private String operate;

    private String resource;

    private Set<PathVariableDefine> variables;

    public String getProject() {
        return project;
    }

    public MemoryPathDefine setProject(String project) {
        this.project = project;
        return this;
    }

    public String getModule() {
        return module;
    }

    public MemoryPathDefine setModule(String module) {
        this.module = module;
        return this;
    }

    public String getOperate() {
        return operate;
    }

    public MemoryPathDefine setOperate(String operate) {
        this.operate = operate;
        return this;
    }

    public String getResource() {
        return resource;
    }

    public MemoryPathDefine setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public Set<PathVariableDefine> getVariables() {
        return variables;
    }

    @Override
    public MemoryPathDefine setVariables(Set<PathVariableDefine> variables) {
        this.variables = variables;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryPathDefine)) return false;
        MemoryPathDefine that = (MemoryPathDefine) o;
        if (project != null ? !project.equals(that.project) : that.project != null) return false;
        if (module != null ? !module.equals(that.module) : that.module != null) return false;
        if (operate != null ? !operate.equals(that.operate) : that.operate != null) return false;
        return resource != null ? resource.equals(that.resource) : that.resource == null;
    }

    @Override
    public int hashCode() {
        int result = project != null ? project.hashCode() : 0;
        result = 31 * result + (module != null ? module.hashCode() : 0);
        result = 31 * result + (operate != null ? operate.hashCode() : 0);
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        return result;
    }

}
