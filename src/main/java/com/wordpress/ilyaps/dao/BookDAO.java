package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Book;
import com.wordpress.ilyaps.models.Category;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(BookDAO.class);

    public static List<Book> findByCategory(Category category) {
        EntityManager em = DBService.getInstance().getEm();

        List books = null;

        try {
            em.getTransaction().begin();
            books = em.createQuery("SELECT b FROM Book b " +
                    "where b.category = :value1 ")
                    .setParameter("value1", category)
                    .getResultList();

        } catch (Exception e) {
            LOGGER.warn("findByCategory", e);
        }finally {
            em.getTransaction().commit();
        }

        return books != null ? books : new ArrayList<>(0);
    }


}
