package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.LikeWantToHelp;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Created by ilyaps on 12.04.16.
 */
public class LikeWantToHelpDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(LikeWantToHelpDAO.class);

    public static boolean exist(LikeWantToHelp like) {
        EntityManager em = DBService.getInstance().getEm();
        try {
            em.getTransaction().begin();
            em.createQuery("SELECT l FROM LikeWantToHelp l " +
                    "where l.wantToHelp = :value1 ")
                    .setParameter("value1", like.getWantToHelp())
                    .getSingleResult();

        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            LOGGER.warn("find by LikeWantToHelpDAO", e);
            return false;
        } finally {
            em.getTransaction().commit();
        }

        return true;
    }
}
