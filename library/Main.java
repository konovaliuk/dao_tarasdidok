package com.library;

import com.library.dao.*;
import com.library.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    static Logger logger = LogManager.getLogger("Create connection");

    public static void main(String[] args) {
//        RoleDao roleDao = DaoFactory.getRoleDao();
//        List<Role> roleList = roleDao.findAll();
//        roleList.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        roleDao.save("Adminn");
//        List<Role> roleListAfterSave = roleDao.findAll();
//        roleListAfterSave.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        Role role = roleDao.findByName("Adminn");
//        roleDao.update(role.getRoleId(), "Admin");
//        List<Role> roleListAfterUpdate = roleDao.findAll();
//        roleListAfterUpdate.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        roleDao.delete(role.getRoleId());
//        List<Role> roleListAfterDelete = roleDao.findAll();
//        roleListAfterDelete.forEach(System.out::println);
//        roleDao.closeConnection();


//        AuthorDao authorDao = DaoFactory.getAuthorDao();
//        List<Author> authors = authorDao.findAll();
//        authors.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        authorDao.save("Isaac", "Asimovv");
//        List<Author> authorsAfterSave = authorDao.findAll();
//        authorsAfterSave.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        Author author1 = authorDao.findById(1);
//        Author author2 = authorDao.findByFullName("Isaac", "Asimovv");
//        authorDao.update(author2.getAuthorId(), "lastname", "Asimov");
//        List<Author> authorsAfterUpdate = authorDao.findAll();
//        authorsAfterUpdate.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        authorDao.delete(author2.getAuthorId());
//        List<Author> authorsAfterDelete = authorDao.findAll();
//        authorsAfterDelete.forEach(System.out::println);
//        authorDao.closeConnection();


//        BookDao bookDao = DaoFactory.getBookDao();
//        List<Book> books = bookDao.findAll();
//        books.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        bookDao.save("William", "Shakespeare", "Othelllo", 320);
//        List<Book> booksAfterSave = bookDao.findAll();
//        booksAfterSave.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        Book bookById = bookDao.findById(2);
//        List<Book> booksByAuthor = bookDao.findByAuthor("William", "Shakespeare");
//        List<Book> booksByTitle = bookDao.findByTitle("Othel");
//        bookDao.update(booksByTitle.get(0).getBookId(), "title", "Othello");
//        List<Book> booksAfterUpdate = bookDao.findAll();
//        booksAfterUpdate.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        bookDao.delete(booksByTitle.get(0).getBookId());
//        List<Book> booksAfterDelete = bookDao.findAll();
//        booksAfterDelete.forEach(System.out::println);
//        bookDao.closeConnection();


//        PersonDao personDao = DaoFactory.getPersonDao();
//        List<Person> people = personDao.findAll();
//        people.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        personDao.save(2,"Steve", "Martin", "steve@email.net", "+380909090909");
//        List<Person> peopleAfterSave = personDao.findAll();
//        peopleAfterSave.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        Person personById = personDao.findById(2);
//        Person personByPhone = personDao.findByPhone("+380909090909");
//        personDao.update(personByPhone.getPersonId(), "lastname", "Jobs");
//        List<Person> peopleAfterUpdate = personDao.findAll();
//        peopleAfterUpdate.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        personDao.delete(personByPhone.getPersonId());
//        List<Person> peopleAfterDelete = personDao.findAll();
//        peopleAfterDelete.forEach(System.out::println);
//        personDao.closeConnection();

//        OrderDao orderDao = DaoFactory.getOrderDao();
//        List<Order> orders = orderDao.findAll();
//        orders.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        orderDao.save(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 24), 2, 1);
//        orderDao.save(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 10), 2, 2);
//        orderDao.save(LocalDate.of(2022, 6, 14), LocalDate.of(2022, 7, 19), 3, 3);
//        orderDao.save(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 24), 3, 1);
//        orderDao.save(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 6, 20), 3, 3);
//        List<Order> ordersAfterSave = orderDao.findAll();
//        ordersAfterSave.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        Order orderById = orderDao.findById(1);
//
//        //
//
//        orderDao.markAsReturned(8);
//        orderDao.markAsLate(9);
//        List<Order> ordersAfterUpdate = orderDao.findAll();
//        ordersAfterUpdate.forEach(System.out::println);
//        System.out.println(">--------------------<");
//        orderDao.delete(10);
//        orderDao.markAsReturned(8);
//        orderDao.markAsReturned(9);
//        orderDao.delete(10);
//        List<Order> ordersAfterDelete = orderDao.findAll();
//        ordersAfterDelete.forEach(System.out::println);
//
//        orderDao.closeConnection();

    }
}
