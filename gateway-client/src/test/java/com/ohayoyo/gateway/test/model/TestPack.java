package com.ohayoyo.gateway.test.model;

import java.io.Serializable;
import java.util.List;

public class TestPack implements Serializable {

    private Integer code ;

    private String msg ;

    private List<TestImage> newslist;

    public Integer getCode() {
        return code;
    }

    public TestPack setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public TestPack setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public List<TestImage> getNewslist() {
        return newslist;
    }

    public TestPack setNewslist(List<TestImage> newslist) {
        this.newslist = newslist;
        return this;
    }

    @Override
    public String toString() {
        return "TestPack{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newslist=" + newslist +
                '}';
    }
}
