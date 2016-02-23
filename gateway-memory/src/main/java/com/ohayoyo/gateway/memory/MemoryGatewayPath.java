package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayPath;

public class MemoryGatewayPath implements GatewayPath<MemoryGatewayVariables> {

    private String project;

    private String module;

    private String operate;

    private String resource;

    private MemoryGatewayVariables variables;

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String getModule() {
        return module;
    }

    @Override
    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String getOperate() {
        return operate;
    }

    @Override
    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Override
    public String getResource() {
        return resource;
    }

    @Override
    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public MemoryGatewayVariables getVariables() {
        return variables;
    }

    @Override
    public void setVariables(MemoryGatewayVariables variables) {
        this.variables = variables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayPath)) return false;
        MemoryGatewayPath that = (MemoryGatewayPath) o;
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
