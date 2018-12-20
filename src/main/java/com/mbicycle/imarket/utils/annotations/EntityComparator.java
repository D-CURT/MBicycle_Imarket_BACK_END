package com.mbicycle.imarket.utils.annotations;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EntityComparator {
    String comparatorType() default "";
}
