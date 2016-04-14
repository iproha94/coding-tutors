package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.models.WantToHelp;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class WantToHelpDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(WantToHelpDAO.class);

    public static List<WantToHelp> findByTaskId(Task task) {
        EntityManager em = DBService.getInstance().getEm();

        List helpers = null;
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

    public static void addWantToHelp(Task task, WantToHelp help) {
        help.setTask(task);
        WantToHelpDAO.insert(help);

        task.incCountWantToHelp();
        TaskDAO.update(task);
    }


    public static WantToHelp findByTaskAndHelper(int taskId, Member helper) {
        EntityManager em = DBService.getInstance().getEm();

        WantToHelp wantToHelp = null;
        try {
            em.getTransaction().begin();
            wantToHelp = (WantToHelp) em.createQuery("SELECT w FROM WantToHelp w " +
                    "where w.task.taskId = :value1 and w.memberHelper = :value2 ")
                    .setParameter("value1", taskId)
                    .setParameter("value2", helper)
                    .getSingleResult();

        } catch (Exception e) {
            LOGGER.warn("existByTaskAndHelper by LikeBookDAO", e);
        } finally {
            em.getTransaction().commit();
        }

        return wantToHelp;
    }
}
