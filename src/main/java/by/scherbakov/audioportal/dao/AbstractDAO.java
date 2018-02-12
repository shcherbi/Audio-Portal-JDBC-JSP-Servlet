package by.scherbakov.audioportal.dao;

import java.util.List;

/**
 * Interface {@code AbstractDAO} is used for all dao implementations.
 * Contain common abstract methods.
 *
 * @author ScherbakovIlia
 */
public interface AbstractDAO<T> {
    /**
     * Takes all entities from database
     *
     * @return collection of entities
     */
    List<T> takeAll();

    /**
     * Takes entity by id from database
     *
     * @return entity
     */
    T take(String id);

    /**
     * Updates entity in database
     *
     * @return {@code true} if entity is up to date. {@code false} if entity isn't up to date.
     */
    boolean update(T object);

    /**
     * Delete entity in database
     *
     * @return {@code true} if entity is deleted. {@code false} if entity isn't deleted.
     */
    boolean delete(T object);
}
