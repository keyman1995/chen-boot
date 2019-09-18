package com.chen.boot.chenboot.controller;

import com.chen.boot.chenboot.annotation.ExcelField;
import org.springframework.stereotype.Component;

/**
 * @ClassName AnnotationBean
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/2
 * @Version V1.0
 **/
@Component
public class AnnotationBean {

    @ExcelField(name = "姓名")
    private String name;

    @ExcelField(name = "年龄")
    private Integer age;


}
