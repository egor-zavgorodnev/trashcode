package com.epam.library.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Bookmark {

    @Id
    @Column(name = "bookmark_id")
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "pagenumber")
    private int pageNumber;

    public Bookmark() {
    }

    public Bookmark(UUID id, User user, Book book, int pageNumber) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.pageNumber = pageNumber;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
