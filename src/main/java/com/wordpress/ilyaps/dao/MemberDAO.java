package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Member;
import com.wordpress.ilyaps.services.DBService;
import org.apache.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.EntityManager;

/**
 * Created by ilyap on 25.12.2015.
 */
public class MemberDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class);

    public static Member find(String email, int hashPassword) {
        Member member = find(email);

        if (member != null && member.getHashPassword() != hashPassword) {
            member = null;
        }

        return member;
    }

    public static Member find(String email) {
        EntityManager em = DBService.getInstance().getEm();

        Member member = null;
        try {
            em.getTransaction().begin();
            member = em.find(Member.class, email);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.warn("find", e);
        }

        return member;
    }

    public static boolean update(Member member) {
        EntityManager em = DBService.getInstance().getEm();
        try {
            em.getTransaction().begin();
            em.merge(member);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
