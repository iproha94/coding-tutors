package com.wordpress.ilyaps.services;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ilyap on 25.12.2015.
 */
public class DBService {
    private final static Logger LOGGER = Logger.getLogger(DBService.class);
    private final static DBService INSTANCE = new DBService();
    private EntityManager em;

    public static DBService getInstance() {
        return INSTANCE;
    }

    private DBService() {
        LOGGER.info("Create DBService");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("thePersistenceUnit");
        em = emf.createEntityManager();

        if (em == null) {
            LOGGER.error("em == null");
            throw new NullPointerException();
        }
    }

    public EntityManager getEm() {
        return em;
    }


}
