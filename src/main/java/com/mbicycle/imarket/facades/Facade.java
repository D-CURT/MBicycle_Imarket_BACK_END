package com.mbicycle.imarket.facades;

public interface Facade<T> {
    boolean push(T t, String identifier);

    T pull(T t);
}
