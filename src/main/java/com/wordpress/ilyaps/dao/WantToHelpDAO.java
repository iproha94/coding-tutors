package com.wordpress.ilyaps.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.wordpress.ilyaps.models.WantToHelp;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 25.12.2015.
 */
public class WantToHelpDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(WantToHelpDAO.class);

    @NotNull
    public static List<WantToHelp> findByTaskId(int taskId) {
        EntityManager em = DBService.getInstance().getEm();

        List<WantToHelp> helpers = null;
        try {
            em.getTransaction().begin();
            helpers = em.createQuery("SELECT w FROM WantToHelp w where w.taskId = :taskId")
                    .setParameter("taskId", taskId).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAllOpen", e);
        }finally {
            em.getTransaction().commit();
        }

        return helpers != null ? helpers : new ArrayList<>(0);
    }

    @Nullable
    public static String getCommentForTaskByEmail(int taskId, String memberEmail) {
        EntityManager em = DBService.getInstance().getEm();

        WantToHelp wantToHelp = null;
        try {
            em.getTransaction().begin();
            wantToHelp = (WantToHelp) em
                    .createQuery("SELECT w FROM WantToHelp w where w.taskId = :taskId and w.memberEmail = :memberEmail")
                    .setParameter("taskId", taskId)
                    .setParameter("memberEmail", memberEmail)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.getTransaction().commit();
        }

        return wantToHelp != null ? wantToHelp.getNote() : null ;
    }


}
