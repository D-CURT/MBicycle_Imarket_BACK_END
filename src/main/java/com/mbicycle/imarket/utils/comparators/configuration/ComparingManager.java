package com.mbicycle.imarket.utils.comparators.configuration;

import com.mbicycle.imarket.utils.annotations.EntityComparator;
import org.reflections.Reflections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ComparingManager {
    private Map<String, Comparator> comparators;

    public ComparingManager() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.mbicycle.imarket.utils.comparators");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(EntityComparator.class);

        comparators = new HashMap<>(annotated.size());
        for (Class aClass: annotated) {
            comparators.put(aClass.getSimpleName(), (Comparator) aClass.newInstance());
        }
    }

    public Comparator getComparator(String comparatorType, String orderType) {
        Comparator comparator = comparators.get(comparatorType);
        return orderType.equals("desc") ? comparator.reversed() : comparator;
    }

    public Map<String, Comparator> getComparators() {
        return comparators;
    }
}