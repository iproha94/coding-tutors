package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.services.DBService;

import javax.persistence.EntityManager;

/**
 * Created by ilyap on 21.01.2016.
 */
public abstract class BaseDAO {
    public static <T> boolean insert(T t) {
        EntityManager em = DBService.getInstance().getEm();

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
