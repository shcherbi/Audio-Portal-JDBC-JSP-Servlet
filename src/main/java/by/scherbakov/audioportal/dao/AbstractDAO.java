package by.scherbakov.audioportal.dao;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract List<T> takeAll();

    public abstract T take(String id);

    public abstract void update(T object);

    public abstract void delete(T object);
}
