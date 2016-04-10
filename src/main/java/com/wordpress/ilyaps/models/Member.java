package com.wordpress.ilyaps.models;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERS")
public class Member {
    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "HASHPASSWORD")
    private int hashPassword;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "UNIVERSITYSHORTNAME")
    private String universityShortName;
    @Column(name = "LIKES")
    private int likes = 0;

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUniversityShortName() {
        return universityShortName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashPassword(int password) {
        this.hashPassword = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUniversityShortName(String universityShortName) {
        this.universityShortName = universityShortName;
    }

    public int getHashPassword() {
        return hashPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getLikes() {
        return likes;
    }

    public void incLikes() {
        likes++;
    }

    @Override
    public String toString() {
        return "Member{" +
                " email='" + email + '\'' +
                ", password='" + hashPassword + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", universityShortName=" + universityShortName +
                '}';
    }

    public String getFullname() {
        return firstname + " " + surname;
    }
}
