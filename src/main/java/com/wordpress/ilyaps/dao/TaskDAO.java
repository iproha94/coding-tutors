package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.services.DBService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyap on 25.12.2015.
 */
public class TaskDAO extends BaseDAO {

    public static List<Task> findAllOpen() {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;
        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.isOpen = :value1")
                    .setParameter("value1", true).getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            return null;
        }

        return tasks;
    }

    public static List<Task> findByEmailCreator(String email) {
        EntityManager em = DBService.getInstance().getEm();

        List<Task> tasks = null;
        try {
            em.getTransaction().begin();
            tasks = em.createQuery("SELECT t FROM Task t where t.memberEmail = :value1")
                    .setParameter("value1", email).getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            return null;
        }

        return tasks;
    }


}
