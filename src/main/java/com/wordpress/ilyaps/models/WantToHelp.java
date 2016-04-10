package com.wordpress.ilyaps.models;

import javax.persistence.*;

@Entity
@Table(name = "WANTTOHELPS")
public class WantToHelp {
    @Id
    @Column(name = "WANTTOHELPID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wantToHelpId;
    @ManyToOne
    @JoinColumn(name = "TASKID")
    private Task task;
    @ManyToOne
    @JoinColumn(name = "MEMBEREMAIL")
    private Member memberHelper;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "LEVELOFCOMPLIANCE")
    private int levelOfCompliance = 0;

    public int getLevelOfCompliance() {
        return levelOfCompliance;
    }

    public void setLevelOfCompliance(int levelOfCompliance) {
        this.levelOfCompliance = levelOfCompliance;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setMemberHelper(Member memberHelper) {
        this.memberHelper = memberHelper;
    }

    public void setNote(String comment) {
        this.note = comment;
    }

    public Task getTask() {
        return task;
    }

    public Member getMemberHelper() {
        return memberHelper;
    }

    public String getNote() {
        return note;
    }
}
