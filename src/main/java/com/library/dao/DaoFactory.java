package com.library.dao;

public class DaoFactory {
    private static AuthorDao authorDao = null;
    private static BookDao bookDao = null;
    private static OrderDao orderDao = null;
    private static PersonDao personDao = null;
    private static RoleDao roleDao = null;

    public static AuthorDao getAuthorDao() {
        if (authorDao == null) authorDao = new AuthorDao();
        return authorDao;
    }

    public static BookDao getBookDao() {
        if (bookDao == null) bookDao = new BookDao();
        return bookDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) orderDao = new OrderDao();
        return orderDao;
    }

    public static PersonDao getPersonDao() {
        if (personDao == null) personDao = new PersonDao();
        return personDao;
    }

    public static RoleDao getRoleDao() {
        if (roleDao == null) roleDao = new RoleDao();
        return roleDao;
    }
}
