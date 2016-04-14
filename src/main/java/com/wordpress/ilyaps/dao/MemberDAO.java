package com.wordpress.ilyaps.dao;

import com.wordpress.ilyaps.models.Member;
import org.apache.log4j.Logger;

public class MemberDAO extends BaseDAO {
    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class);

    public static Member find(String email, int hashPassword) {
        Member member = (Member) find(Member.class, email);

        if (member != null && member.getHashPassword() != hashPassword) {
            member = null;
        }

        return member;
    }

}
