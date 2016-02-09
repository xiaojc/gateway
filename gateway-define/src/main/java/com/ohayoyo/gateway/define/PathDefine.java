package com.ohayoyo.gateway.define;


import java.io.Serializable;
import java.util.Set;

public interface PathDefine extends Serializable {

    String getProject();

    PathDefine setProject(String project);

    String getModule();

    PathDefine setModule(String module);

    String getOperate();

    PathDefine setOperate(String operate);

    String getResource();

    PathDefine setResource(String resource);

    Set<VariableDefine> getVariables();

    PathDefine setVariables(Set<VariableDefine> variables);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
