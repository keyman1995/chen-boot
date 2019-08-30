package com.chen.boot.chenboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/8/27
 * @Version V1.0
 **/
@Controller
public class HelloController {


    @GetMapping("go")
    public String helloTest(){
        return "hello";
    }


    public static void main(String[] args) {
        String apiKey = "351027f211ba417dacc9389f0b289410";
        String apiSecreat = "0fbe3785c1ffcb22f296477fb8883b8f";
        String temp = String.format("%s:%s", apiKey, apiSecreat);
        String encrypt = Base64.getEncoder().encodeToString(temp.getBytes());
        String authorization = String.format("Basic %s", encrypt);
    }


}
