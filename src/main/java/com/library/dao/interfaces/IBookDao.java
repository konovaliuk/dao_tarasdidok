package com.library.dao.interfaces;

import com.library.entities.Book;

import java.util.List;

public interface IBookDao {
    List<Book> findAll() throws Exception;

    Book findById(long id) throws Exception;

    List<Book> findByTitle(String title) throws Exception;

    List<Book> findByAuthor(long authorId) throws Exception;

    long save(long authorId, String title, long pages, long placement) throws Exception;

    long update(long id, String property, String value);

    void delete(long id);
}
