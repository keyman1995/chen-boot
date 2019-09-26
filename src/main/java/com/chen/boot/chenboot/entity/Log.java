package com.chen.boot.chenboot.entity;

/**
 * @ClassName Log
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/23
 * @Version V1.0
 **/
public class Log {

    private String id;

    private String content;

    public Log(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
