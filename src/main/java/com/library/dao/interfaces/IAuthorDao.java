package com.library.dao.interfaces;

import com.library.entities.Author;

import java.util.List;

public interface IAuthorDao {
    List<Author> findAll() throws Exception;

    Author findById(long id) throws Exception;

    long save(String name, String lastname) throws Exception;
    long update(long id, String property, String value);
    void delete(long id);
}
