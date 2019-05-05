package com.ecpss.templet.infrastructure.util;

import com.ecpss.templet.domain.util.Registry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by chenpang on 2018/5/23 21:12.
 */
@Component
public class RegistryWithAC extends Registry implements ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        super.setRegistry(this);
    }

    public <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

}
