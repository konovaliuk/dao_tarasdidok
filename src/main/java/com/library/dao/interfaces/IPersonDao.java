package com.library.dao.interfaces;

import com.library.entities.Person;

import java.util.List;

public interface IPersonDao {
    List<Person> findAll() throws Exception;

    Person findById(long id) throws Exception;

    long save(long roleId, String name, String lastname, String email, String phone) throws Exception;

    long update(long id, String property, String value);

    void delete(long id);
}
