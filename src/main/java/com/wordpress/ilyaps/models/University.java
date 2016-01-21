package com.wordpress.ilyaps.models;

import java.util.*;

/**
 * Created by ilyap on 28.12.2015.
 */
public class University {
    private static final Map<String, String> universities;

    static
    {
        universities = new HashMap<String, String>();
        universities.put("bmstu", "МГТУ");
        universities.put("spbu", "СПБГУ");
        universities.put("msu", "МГУ");
    }

    public static Set<String> getSet() {
        return universities.keySet();
    }

    public static Map<String, String> getMap() {
        return universities;
    }
}
