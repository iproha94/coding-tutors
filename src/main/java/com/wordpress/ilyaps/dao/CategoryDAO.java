package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Category;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.*;

public class CategoryDAO extends BaseDAO{
    private static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    public static Map<Integer, Category> getMap() {
        List<Category> categoryList = findAll();
        Map<Integer, Category> categories = new HashMap<>();

        for (Category category : categoryList) {
            categories.put(category.getId(), category);
        }

        return categories;
    }

    public static List<Category> findAll() {
        EntityManager em = DBService.getInstance().getEm();

        List categories = null;
        try {
            em.getTransaction().begin();
            categories = em.createQuery("SELECT w FROM Category w")
                    .getResultList();

        } catch (Exception e) {
            LOGGER.warn("findAll", e);
        }finally {
            em.getTransaction().commit();
        }

        return categories != null ? categories : new ArrayList<>(0);
    }



}
