package com.wordpress.ilyaps.models;

import com.wordpress.ilyaps.dao.WantToHelpDAO;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "MEMBEREMAIL")
    private Member memberNeed;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "OPEN")
    private boolean isOpen = true;
    @Column(name = "COUNTWANTTOHELP")
    private int countWantToHelp = 0;

    public int getCountWantToHelp() {
        return countWantToHelp;
    }

    public void incCountWantToHelp() {
        countWantToHelp++;
    }

    public void setMemberNeed(Member memberNeed) {
        this.memberNeed = memberNeed;
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

    public Member getMemberNeed() {
        return memberNeed;
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

    public List<WantToHelp> getListWantToHelps() {
        return WantToHelpDAO.findByTaskId(this);
    }
}
