package com.library.entities;

import java.util.Objects;

public class Author {
    private long authorId;
    private String name;
    private String lastname;

    public Author(long authorId, String name, String lastname) {
        this.authorId = authorId;
        this.name = name;
        this.lastname = lastname;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean equals(Author a) {
        return (getName().equals(a.getName()) && (getLastname().equals(a.getLastname())));
    }


    public String toString() {
        return this.getAuthorId() + "\t" + this.getName() + "\t" + this.getLastname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) && lastname.equals(author.lastname);
    }
}
