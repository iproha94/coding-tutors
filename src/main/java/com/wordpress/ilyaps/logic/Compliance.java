package com.wordpress.ilyaps.logic;

import com.wordpress.ilyaps.models.Member;

/**
 * Created by ilyap on 22.01.2016.
 */
public class Compliance {
    public static int getComplianceMembers(Member member1, Member member2) {
        int level = 0;
        if (member1.getUniversityShortName().equals(member2.getUniversityShortName())) {
            level += 10;
        }

        return level;
    }
}
