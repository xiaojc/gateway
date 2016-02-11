package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

public interface PathDefine extends ObjectDefine {

    String getProject();

    PathDefine setProject(String project);

    String getModule();

    PathDefine setModule(String module);

    String getOperate();

    PathDefine setOperate(String operate);

    String getResource();

    PathDefine setResource(String resource);

    VariablesDefine getVariables();

    PathDefine setVariables(VariablesDefine variables);

}
