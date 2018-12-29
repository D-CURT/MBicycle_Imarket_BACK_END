package com.mbicycle.imarket.utils.converters;

import org.springframework.stereotype.Component;

@Component
public interface Converter<S, T> {

    void convert(S source, T target);

    T convert(S source);
}
