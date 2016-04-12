package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Task;
import com.wordpress.ilyaps.models.University;
import com.wordpress.ilyaps.models.WantToHelp;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.*;

public class UniversityDAO extends BaseDAO{
    private static final Logger LOGGER = Logger.getLogger(UniversityDAO.class);

    public static Set<String> getSet() {
        List<University> universitiesList = findAll();
        Set<String> universities = new HashSet<>();

        for (University university : universitiesList) {
            universities.add(university.getShortName());
        }

        return universities;
    }

    public static Map<String, String> getMap() {
        List<University> universitiesList = findAll();
        Map<String, String>  universities = new HashMap<>();

        for (University university : universitiesList) {
            universities.put(university.getShortName(), university.getFullName());
        }

        return universities;
    }

    public static List<University> findAll() {
        EntityManager em = DBService.getInstance().getEm();

        List<University> universities = null;
        try {
            em.getTransaction().begin();
            universities = em.createQuery("SELECT w FROM University w")
                    .getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAll", e);
        }finally {
            em.getTransaction().commit();
        }

        return universities != null ? universities : new ArrayList<>(0);
    }



}
