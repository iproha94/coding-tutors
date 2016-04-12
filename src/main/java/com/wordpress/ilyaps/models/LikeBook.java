package com.wordpress.ilyaps.models;

import javax.persistence.*;

@Entity
@Table(name = "LIKEBOOK")
public class LikeBook {
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBEREMAIL")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "BOOKID")
    private Book book;

    public LikeBook(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public LikeBook() {
    }

    public Member getMember() {

        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "LikeBook{" +
                "member=" + member +
                ", book=" + book +
                '}';
    }
}
