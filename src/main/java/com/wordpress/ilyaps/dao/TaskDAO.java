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
    public static List<Task> findAllOpen(int start, int count) {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;

        if (start < 0) {
            start = 0;
        }

        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.isOpen = :value1 ORDER BY t.dateTimeField desc")
                    .setFirstResult(start)
                    .setMaxResults(count)
                    .setParameter("value1", true).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAllOpen", e);
        }finally {
            em.getTransaction().commit();
        }

        return tasks != null ? tasks : new ArrayList<>(0);
    }

    @NotNull
    public static List<Task> findTasksByMemberNeed(Member memberNeed) {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;
        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.memberNeed = :value1  ORDER BY t.dateTimeField desc")
                    .setParameter("value1", memberNeed).getResultList();

        } catch (Exception e) {
            LOGGER.warn("findTasksByMemberNeed", e);
        }finally {
            em.getTransaction().commit();
        }

        return tasks != null ? tasks : new ArrayList<>(0);
    }

    @Nullable
    public static long countByMember(Member member) {
        EntityManager em = DBService.getInstance().getEm();

        long count = 0;
        try {
            em.getTransaction().begin();
            count =  em.createQuery("SELECT count(t) FROM Task t where t.memberNeed = :value1", Long.class)
                    .setParameter("value1", member)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.warn("count", e);
        } finally {
            em.getTransaction().commit();
        }

        return count;
    }
}
