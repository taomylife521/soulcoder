package com.soulcoder.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * The interface Base dao.
 *
 * @param <T> the type parameter
 */
public interface BaseDao<T> {

    /**
     * Save.
     *
     * @param t the t
     */
    void save(T t);

    /**
     * Save.
     *
     * @param map the map
     */
    void save(Map<String, Object> map);

    /**
     * Save batch.
     *
     * @param list the list
     */
    void saveBatch(List<T> list);

    /**
     * Update int.
     *
     * @param t the t
     * @return the int
     */
    int update(T t);

    /**
     * Update int.
     *
     * @param map the map
     * @return the int
     */
    int update(Map<String, Object> map);

    /**
     * Delete int.
     *
     * @param id the id
     * @return the int
     */
    int delete(Object id);

    /**
     * Delete int.
     *
     * @param map the map
     * @return the int
     */
    int delete(Map<String, Object> map);

    /**
     * Delete batch int.
     *
     * @param id the id
     * @return the int
     */
    int deleteBatch(Object[] id);

    /**
     * Query object t.
     *
     * @param id the id
     * @return the t
     */
    T queryObject(Object id);

    /**
     * Query list list.
     *
     * @param map the map
     * @return the list
     */
    List<T> queryList(Map<String, Object> map);

    /**
     * Query list list.
     *
     * @param id the id
     * @return the list
     */
    List<T> queryList(Object id);

    /**
     * Query total int.
     *
     * @param map the map
     * @return the int
     */
    int queryTotal(Map<String, Object> map);

    /**
     * Query total int.
     *
     * @return the int
     */
    int queryTotal();
}
