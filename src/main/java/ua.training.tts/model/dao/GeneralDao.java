package ua.training.tts.model.dao;

import java.util.List;

public interface GeneralDao<T, ID, Param, Keys>{

    /**
     * Creates entry in database
     * @param entity
     */
    void create(T entity);

    /**
     * Returns entry from database by id
     * @param id
     */
    T findById(ID id);

    /**
     * Returns all entries from database
     * @return result list
     */
    List<T> findAll();

    /**
     * Updates entry in database
     * @param entity
     */
    void update(T entity);

    /**
     * Deletes entry in database by id
     * @param id
     */
    void delete(ID id);

    /**
     * Returns entity's ID by provided keys
     * @param keys
     */
    ID findIdByKeys(Keys... keys);

    /**
     * Returns entity's parameter by provided keys
     * @param keys
     */
    Param findParamByKeys(Param param, Keys... keys);
}
