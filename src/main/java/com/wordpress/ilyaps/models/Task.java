package com.wordpress.ilyaps.models;

import javax.persistence.*;

/**
 * Created by ilyap on 25.12.2015.
 */
@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @Column(name = "TASKID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    @Column(name = "MEMBEREMAIL")
    private String memberEmail;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "OPEN")
    private boolean isOpen = true;
    @Column(name = "COUNTWANTTOHELP")
    private int countWantToHelp = 0;

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
