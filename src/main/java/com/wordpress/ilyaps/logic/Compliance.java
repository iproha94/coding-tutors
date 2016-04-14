package com.wordpress.ilyaps.logic;

import com.wordpress.ilyaps.models.Member;

public class Compliance {
    public static int getComplianceMembers(Member member1, Member member2) {
        int level = 0;
        if (member1.getUniversityShortName().equals(member2.getUniversityShortName())) {
            level += 10;
        }

        return level;
    }
}
