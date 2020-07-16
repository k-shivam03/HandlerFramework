package com.handler.framework;

import java.lang.reflect.*;
import java.util.*;

import com.handler.framework.annotation.*;

import com.google.gson.*;

public class Service {
    private String path;
    private Class c;
    private Method method;
    private Object instance;
    public Service(String path, Class c, Method method) {
        this.path = path;
        this.c = c;
        this.method = method;
    }
    public String invoke(String jsonString) throws Exception {
        Object[] obj;
        List<Object> list = new ArrayList<>();
        Gson gson = new Gson();

        if (instance == null) instance = c.newInstance();
        if (this.method.getParameters().length > 0) {
            for (Parameter parameter: this.method.getParameters()) {
                RequestData rd;
                if (parameter.isAnnotationPresent(RequestData.class)) {
                    rd = (RequestData) parameter.getAnnotation(RequestData.class);
                    Class<?> cls = parameter.getType();
                    list.add(gson.fromJson(jsonString, cls));
                }
            }
            obj = list.toArray();
            return method.invoke(instance, obj).toString();
        } else {
            return method.invoke(instance).toString();
        }
    }
}