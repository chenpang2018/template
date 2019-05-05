package com.ecpss.templet.domain.util;

/**
 * Created by chenpang on 2018/5/23 21:19.
 */
public class Registry {
    private static Registry registry = new Registry();

    public void setRegistry(Registry aregistry) {
        registry = aregistry;
    }

    public static Registry getInstance() {
        return registry;
    }

    public static <T> T queryBean(Class<T> t) {
        return getInstance().getBean(t);
    }

    public <T> T getBean(Class<T> t) {
        return null;
    }
}
