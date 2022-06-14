package com.library.entities;

public class Book {
    private long bookId;
    private long authorId;
    private String title;
    private long pages;
    private Long placement;
    private Long reader;

    public Book(long bookId, long authorId, String title, long pages, long placement, long reader){
        this.bookId = bookId;
        this.authorId = authorId;
        this.title = title;
        this.pages = pages;
        this.placement = placement;
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

    public Long getPlacement() {
        return placement;
    }

    public void setPlacement(Long placement) {
        this.placement = placement;
    }

    public Long getReader() {
        return reader;
    }

    public void setReader(Long reader) {
        this.reader = reader;
    }

    public String toString() {
        return this.getBookId() + "\t" + this.getAuthorId() + "\t" + this.getTitle() + "\t" + this.getPages()
                + "\t" + this.getPlacement() + "\t" + this.getReader();
    }
}
