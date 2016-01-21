package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

/**
 * Created by ilyap on 21.01.2016.
 */
public abstract class BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class);

    public static <T> boolean insert(T t) {
        EntityManager em = DBService.getInstance().getEm();

        try {
            em.getTransaction().begin();
            em.persist(t);
        } catch (Exception e) {
            LOGGER.warn("insert", e);
            return false;
        } finally {
            em.getTransaction().commit();
        }

        return true;
    }

    public static  <T> boolean update(T t) {
        EntityManager em = DBService.getInstance().getEm();
        try {
            em.getTransaction().begin();
            em.merge(t);
        } catch (Exception e) {
            LOGGER.warn("update", e);
            return false;
        } finally {
            em.getTransaction().commit();
        }

        return true;
    }
}
