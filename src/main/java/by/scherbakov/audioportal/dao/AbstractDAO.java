package by.scherbakov.audioportal.dao;

import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract List<T> takeAll();

    public abstract T take(String id);

    public abstract boolean update(T object);

    public abstract boolean delete(T object);
}
