package com.epam.library.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    private UUID id;

    @Column(name = "bookname")
    private String bookName;

    @Column(name = "releaseyear")
    private int releaseYear;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "pagecount")
    private int pageCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {}

    public Book(UUID id, String bookName, int releaseYear, String ISBN, String publisher, int pageCount, Author author) {
        this.id = id;
        this.bookName = bookName;
        this.releaseYear = releaseYear;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", releaseYear=" + releaseYear +
                ", ISBN='" + ISBN + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pageCount=" + pageCount +
                ", author=" + author +
                '}';
    }
}
