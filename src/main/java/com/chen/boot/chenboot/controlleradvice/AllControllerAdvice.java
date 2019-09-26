package com.chen.boot.chenboot.controlleradvice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @ClassName AllControllerAdvice
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/6
 * @Version V1.0
 **/
//@ControllerAdvice
public class AllControllerAdvice {

    @Value("${server.servlet.context-path}")
    private String contentPath;

    @ModelAttribute
    public Model handleRequest(Model model) {
        model.addAttribute("contentPath", contentPath);
        return model;
    }

}
