package com.wordpress.ilyaps.models;

import javax.persistence.*;

/**
 * Created by ilyap on 25.12.2015.
 */
@Entity
@Table(name = "WANTTOHELP")
public class WantToHelp {
    @Id
    @Column(name = "WANTTOHELPID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wantToHelpId;
    @Column(name = "TASKID")
    private int taskId;
    @Column(name = "MEMBEREMAIL")
    private String memberEmail;
    @Column(name = "COMMENT")
    private String comment;

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTaskId() {

        return taskId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getComment() {
        return comment;
    }
}
