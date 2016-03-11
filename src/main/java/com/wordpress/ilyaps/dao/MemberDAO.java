package com.wordpress.ilyaps.dao;

import com.sun.istack.internal.Nullable;
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

    @Nullable
    public static Member find(String email, int hashPassword) {
        Member member = (Member) find(Member.class, email);

        if (member != null && member.getHashPassword() != hashPassword) {
            member = null;
        }

        return member;
    }

}
