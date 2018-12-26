package com.mbicycle.imarket.facades;

public interface Facade<DTO, Entity> {
    Entity push(DTO DTO, String identifier);

    DTO pull(Entity entity);
}
