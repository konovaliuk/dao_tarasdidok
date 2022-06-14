package com.library.entities;

public class Book {
    private long bookId;
    private long authorId;
    private String title;
    private long pages;
    private long reader;

    public Book(long bookId, long authorId, String title, long pages, long reader) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.title = title;
        this.pages = pages;
        this.reader = reader;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getReader() {
        return reader;
    }

    public void setReader(long reader) {
        this.reader = reader;
    }

    public String toString() {
        return this.getBookId() + "\t" + this.getAuthorId() + "\t" + this.getTitle() + "\t" + this.getPages()
                /*+ "\t" + this.getReader()*/;
    }
}
