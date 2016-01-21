package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.models.WantToHelp;
import com.wordpress.ilyaps.services.DBService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ilyap on 25.12.2015.
 */
public class WantToHelpDAO extends BaseDAO {


    public static List<WantToHelp> findByTaskId(int taskId) {
        EntityManager em = DBService.getInstance().getEm();

        List<WantToHelp> helpers = null;
        try {
            em.getTransaction().begin();
            helpers = em.createQuery("SELECT w FROM WantToHelp w where w.taskId = :taskId")
                    .setParameter("taskId", taskId).getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            return null;
        }

        return helpers;
    }


}
