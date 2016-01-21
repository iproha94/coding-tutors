package com.wordpress.ilyaps.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 25.12.2015.
 */
public class TaskDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(TaskDAO.class);

    @NotNull
    public static List<Task> findAllOpen() {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;

        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.isOpen = :value1")
                    .setParameter("value1", true).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAllOpen", e);
        }finally {
            em.getTransaction().commit();
        }

        return tasks != null ? tasks : new ArrayList<>(0);
    }

    @NotNull
    public static List<Task> findTasksByEmailCreator(String email) {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;
        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.memberEmail = :value1")
                    .setParameter("value1", email).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAllOpen", e);
        }finally {
            em.getTransaction().commit();
        }

        return tasks != null ? tasks : new ArrayList<>(0);
    }

    @Nullable
    public static Task find(int taskId) {
        EntityManager em = DBService.getInstance().getEm();

        Task task = null;
        try {
            em.getTransaction().begin();
            task = em.find(Task.class, taskId);
        } catch (Exception e) {
            LOGGER.warn("find", e);
        } finally {
            em.getTransaction().commit();
        }

        return task;
    }
}
