package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public abstract class BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(BaseDAO.class);

    public static void drop(Class clazz) {
        EntityManager em = DBService.getInstance().getEm();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM " + clazz.getSimpleName() + " e").executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("drop", e);
        } finally {
            em.getTransaction().commit();
        }
    }

    public static long count(Class clazz) {
        EntityManager em = DBService.getInstance().getEm();

        long count = 0;
        try {
            em.getTransaction().begin();
            count = em.createQuery("SELECT count(t) FROM " + clazz.getSimpleName() + " t", Long.class)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("count", e);
        } finally {
            em.getTransaction().commit();
        }

        return count;
    }

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

    public static <T> Object find(Class clazz, T id) {
        LOGGER.info(id.toString());
        EntityManager em = DBService.getInstance().getEm();

        Object el = null;
        try {
            em.getTransaction().begin();
            el = em.find(clazz, id);
        } catch (Exception e) {
            LOGGER.warn("find", e);
        } finally {
            em.getTransaction().commit();
        }

        return el;
    }
}
