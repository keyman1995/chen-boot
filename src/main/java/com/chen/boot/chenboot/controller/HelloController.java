package com.chen.boot.chenboot.controller;

import com.chen.boot.chenboot.config.ReportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/8/27
 * @Version V1.0
 **/
@Controller
public class HelloController {

    @Value("zhangsan")
    private String name;

    @Autowired
    private ReportFactory reportFactory;

    @Autowired
    private DoubleColorUtils doubleColorUtils;

    @ModelAttribute
    public String getContentPath() {
        return "/boot";
    }


    @GetMapping("go")
    public String helloTest() {
        reportFactory.getReportServiceMap();
        return "hello";
    }


    @GetMapping("sendMessage")
    @ResponseBody
    public String sengMessage(String platform) {


        doubleColorUtils.doCollect();

        return "SUCCESEE";
    }


    public static void main(String[] args) {
        String str = "";
        str.split(",");
    }


}
