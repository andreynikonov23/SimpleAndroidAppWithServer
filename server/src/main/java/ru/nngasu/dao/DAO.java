package ru.nngasu.dao;

import java.util.List;

public interface DAO <Entity, Id> {
    List<Entity> showAll();
    Entity findById(Id id);
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
