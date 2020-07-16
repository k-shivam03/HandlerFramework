package com.handler.framework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.PARAMETER
})
public @interface RequestData {
    public String value();
}