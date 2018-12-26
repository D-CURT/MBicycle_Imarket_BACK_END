package com.mbicycle.imarket.utils.converters;

public interface Converter<T, R> {
    R convert(T t);
}
