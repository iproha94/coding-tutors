package com.wordpress.ilyaps.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;
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

    public static void drop() {
        EntityManager em = DBService.getInstance().getEm();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM WantToHelp e").executeUpdate();
        } catch (Exception e) {
            LOGGER.warn("drop", e);
        } finally {
            em.getTransaction().commit();
        }
    }

    @NotNull
    public static List<WantToHelp> findByTaskId(Task task) {
        EntityManager em = DBService.getInstance().getEm();

        List<WantToHelp> helpers = null;
        try {
            em.getTransaction().begin();
            helpers = em.createQuery("SELECT w FROM WantToHelp w where w.task = :task")
                    .setParameter("task", task).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAllOpen", e);
        }finally {
            em.getTransaction().commit();
        }

        return helpers != null ? helpers : new ArrayList<>(0);
    }

    @Nullable
    public static String getNoteForTaskByEmail(Task task, Member member) {
        EntityManager em = DBService.getInstance().getEm();

        WantToHelp wantToHelp = null;
        try {
            em.getTransaction().begin();
            wantToHelp = (WantToHelp) em
                    .createQuery("SELECT w FROM WantToHelp w where w.task = :task and w.memberHelper = :member")
                    .setParameter("task", task)
                    .setParameter("member", member)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.getTransaction().commit();
        }

        return wantToHelp != null ? wantToHelp.getNote() : null ;
    }

}
