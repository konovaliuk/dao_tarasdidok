package com.library;

import com.library.dao.*;
import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class Main {
    static Logger logger = LogManager.getLogger("Create connection");

    public static void main(String[] args) {
        //RoleDao roleDao = DaoFactory.getRoleDao();
        //List<Role> roleList = roleDao.findAll();
        //roleList.forEach(System.out::println);
        //roleDao.update(1, "Librarian");
        //List<Role> roleListAfterInsert = roleDao.findAll();
        //roleListAfterInsert.forEach(System.out::println);
        //roleDao.delete(22);
        //List<Role> roleListAfterDelete = roleDao.findAll();
        //roleListAfterDelete.forEach(System.out::println);

        //roleDao.closeConnection();

        /*
        AuthorDao authorDao = DaoFactory.getAuthorDao();
        authorDao.save("Dante", "Alighiery");
        List<Author> authors = authorDao.findAll();
        authors.forEach(System.out::println);
        authorDao.closeConnection();


        BookDao bookDao = DaoFactory.getBookDao();
        bookDao.save(2, "The Divine Comedy", 848, 2);
        List<Book> books = bookDao.findAll();
        books.forEach(System.out::println);
        bookDao.closeConnection();
        */

        PersonDao personDao = DaoFactory.getPersonDao();
        personDao.save(1, "Taras", "Didok", "tdidok@smth.com", "+380505050505");

    }
}
