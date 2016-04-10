package com.wordpress.ilyaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ilyap on 28.12.2015.
 */

@Entity
@Table(name = "UNIVERSITY")
public class University {
    @Id
    @Column(name = "SHORTNAME")
    private String shortName;
    @Column(name = "FULLNAME")
    private String fullName;

    public University(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public University() {
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

}
