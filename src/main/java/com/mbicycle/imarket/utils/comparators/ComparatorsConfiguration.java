package com.mbicycle.imarket.utils.comparators;

import com.mbicycle.imarket.utils.annotations.EntityComparator;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class ComparatorsConfiguration {

    @Bean
    public Map<String, Comparator> comparators() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.mbicycle.imarket.utils.comparators");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(EntityComparator.class);
        Map<String, Comparator> comparators = null;
        if (annotated.size() > 0) {
            comparators = new HashMap<>(annotated.size());
            for (Class aClass: annotated) {
                comparators.put(aClass.getSimpleName(), (Comparator) aClass.newInstance());
            }
        }
        return comparators;
    }
}