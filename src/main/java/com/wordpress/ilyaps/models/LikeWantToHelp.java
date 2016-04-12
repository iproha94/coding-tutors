package com.wordpress.ilyaps.models;

import javax.persistence.*;

@Entity
@Table(name = "LIKEWANTTOHELP")
public class LikeWantToHelp {
    @Id
    @ManyToOne
    @JoinColumn(name = "WANTTOHELPID")
    private WantToHelp wantToHelp;

    public LikeWantToHelp(WantToHelp wantToHelp) {
        this.wantToHelp = wantToHelp;
    }

    public LikeWantToHelp() {
    }

    public WantToHelp getWantToHelp() {
        return wantToHelp;
    }

    public void setWantToHelp(WantToHelp wantToHelp) {
        this.wantToHelp = wantToHelp;
    }
}
