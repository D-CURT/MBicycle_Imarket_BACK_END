package com.mbicycle.imarket.utils.converters;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    private Class<T> targetClass;

    public AbstractConverter() {
        init(((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1]
                .getTypeName());
    }

    @Override
    public T convert(S source) {
        try {
            T defaultTarget = targetClass.newInstance();
            convert(source, defaultTarget);
            return defaultTarget;
        } catch (Exception e) {
            throw new RuntimeException("Conversion error: ", e);
        }
    }

    @SuppressWarnings("unchecked")
    protected void init(String targetClass) {
        try {
            this.targetClass = (Class<T>) Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
