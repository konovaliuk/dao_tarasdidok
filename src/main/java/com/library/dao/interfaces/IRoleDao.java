package com.library.dao.interfaces;

import com.library.entities.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findAll() throws Exception;
    long save(String roleName) throws Exception;
    long update(long id, String roleName);
    void delete(long id) throws Exception;
}
