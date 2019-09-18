package com.chen.boot.chenboot.config;

import com.chen.boot.chenboot.common.ReportService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ReportFactory
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/9/4
 * @Version V1.0
 **/
@Component
public class ReportFactory implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private Map<String, ReportService> reportServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        reportServiceMap = new ConcurrentHashMap<>();
        reportServiceMap =BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,ReportService.class,true,false);
    }

    public Map<String, ReportService> getReportServiceMap() {
        return reportServiceMap;
    }
}
