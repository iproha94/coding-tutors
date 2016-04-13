package com.wordpress.ilyaps.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "BOOK")
public class Book {
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                '}';
    }

    @Id
    @Column(name = "BOOKID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    @ManyToOne
    @JoinColumn(name = "CATEGORYID")
    private Category category;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "LIKES")
    private int likes = 0;

    public Book(String title, String author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int incLikes() {
        likes++;
        return likes;
    }
}
