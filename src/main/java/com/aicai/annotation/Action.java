package com.aicai.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Action {
    String DEFAULT_VALUE = "DEFAULT_VALUE";

    String value() default DEFAULT_VALUE;

    Result[] results() default {};

    // InterceptorRef[] interceptorRefs() default {};

    String[] params() default {};

    // ExceptionMapping[] exceptionMappings() default {};

    String className() default DEFAULT_VALUE;
}