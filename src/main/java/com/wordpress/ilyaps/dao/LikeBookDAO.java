package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.LikeBook;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by ilyaps on 12.04.16.
 */
public class LikeBookDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(LikeBookDAO.class);

    public static boolean exist(LikeBook like) {
        EntityManager em = DBService.getInstance().getEm();
        try {
            em.getTransaction().begin();
            em.createQuery("SELECT l FROM LikeBook l " +
                    "where l.book = :value1 and l.member = :value2 ")
                    .setParameter("value1", like.getBook())
                    .setParameter("value2", like.getMember())
                    .getSingleResult();

        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            LOGGER.warn("find by LikeBookDAO", e);
            return false;
        } finally {
            em.getTransaction().commit();
        }

        return true;
    }
}
