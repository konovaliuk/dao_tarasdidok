package com.library.dao;

import com.library.ConnectionPool;
import com.library.dao.interfaces.IBookDao;
import com.library.entities.Author;
import com.library.entities.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    protected Connection connection = null;
    protected final Logger logger = LogManager.getLogger("Book Dao");
    protected final String findAllSqlQuery = "select * from " + getTableName() + "";
    protected final String findByIdSqlQuery = "select * from " + getTableName() + " where book_id=?";
    protected final String findByAuthorSqlQuery = "select * from " + getTableName() + " where author_id = ?";
    protected final String findByTitleSqlQuery = "select * from " + getTableName() + " where title like ?";
    protected final String saveSqlQuery = "insert into " + getTableName() +
            " (author_id, title, pages)" + " values (?, ?, ?)";
    protected final String deleteSqlQuery = "delete from " + getTableName() + " where book_id=?";

    public BookDao() {
        this.connection = ConnectionPool.createConnection();
    }

    public void closeConnection() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = ConnectionPool.createConnection();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    String getTableName() {
        return "book";
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        long bookId = resultSet.getLong("book_id");
        long authorId = resultSet.getLong("author_id");
        String title = resultSet.getString("title");
        long pages = resultSet.getLong("pages");
        long reader = resultSet.getLong("reader");
        return new Book(bookId, authorId, title, pages, reader);
    }

    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findAllSqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBook(resultSet);
                bookList.add(book);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return bookList;
    }

    public List<Book> findByTitle(String titlePart) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByTitleSqlQuery);
            preparedStatement.setString(1, "%" + titlePart + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBook(resultSet);
                bookList.add(book);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return bookList;
    }

    public List<Book> findByAuthor(long authorId) {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByAuthorSqlQuery);
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBook(resultSet);
                bookList.add(book);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return bookList;
    }

    public List<Book> findByAuthor(String name, String lastname) {
        List<Book> bookList = new ArrayList<>();
        try {
            AuthorDao authorDao = DaoFactory.getAuthorDao();
            Author author = authorDao.findByFullName(name, lastname);
            if (author == null) throw new Exception("Author not found");
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByAuthorSqlQuery);
            preparedStatement.setLong(1, author.getAuthorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBook(resultSet);
                bookList.add(book);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return bookList;
    }

    public Book findById(long id) {
        Book book = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByIdSqlQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = getBook(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return book;
    }

    public long save(long authorId, String title, long pages) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setLong(1, authorId);
            preparedStatement.setString(2, title);
            preparedStatement.setLong(3, pages);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long save(String authorName, String authorLastname, String title, long pages) {
        long rowsAffected = 0;
        try {
            AuthorDao authorDao = DaoFactory.getAuthorDao();
            Author author = authorDao.findByFullName(authorName, authorLastname);
            if (author == null) throw new Exception("Author not found");
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setLong(1, author.getAuthorId());
            preparedStatement.setString(2, title);
            preparedStatement.setLong(3, pages);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String property, String value) {
        final String updateSqlQuery = "update `" + getTableName() + "` set " + property + "=? where book_id=?";
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSqlQuery);
            preparedStatement.setString(1, value);
            preparedStatement.setLong(2, id);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String property, long value) {
        final String updateSqlQuery = "update `" + getTableName() + "` set " + property + "=? where book_id=?";
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSqlQuery);
            preparedStatement.setLong(1, value);
            preparedStatement.setLong(2, id);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(deleteSqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception exception) {
            logger.error(exception);
        }
    }
}
