package com.wordpress.ilyaps.models;

import com.sun.istack.internal.NotNull;
import com.wordpress.ilyaps.dao.TaskDAO;
import com.wordpress.ilyaps.dao.WantToHelpDAO;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilyap on 25.12.2015.
 */

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

    public Member() {
//        memberId = 0;
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

    @NotNull
    public List<Task> getListMyTasks() {
        return TaskDAO.findTasksByMemberNeed(this);
    }

    public String getMyNoteForTask(Task task) {
        return WantToHelpDAO.getNoteForTaskByEmail(task, this);
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
