package com.library.dao;

import com.library.ConnectionPool;
import com.library.dao.interfaces.IAuthorDao;
import com.library.entities.Author;
import com.library.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements IAuthorDao {
    protected Connection connection = null;
    protected final Logger logger = LogManager.getLogger("Author Dao");
    protected final String findAllSqlQuery = "select * from " + getTableName() + "";
    protected final String findByIdSqlQuery = "select * from" + getTableName() + " where author_id=?";
    protected final String saveSqlQuery = "insert into " + getTableName() + " (name, lastname)" + " values (?, ?)";
    protected final String updateSqlQuery = "update " + getTableName() + " set ? = ? where author_id = ?";
    protected final String deleteSqlQuery = "delete from " + getTableName() + " where author_id=?";

    public AuthorDao(){ this.connection = ConnectionPool.createConnection(); }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.warn(exception);
        }
    }

    String getTableName() {
        return "author";
    }

    private Author getAuthor(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong("author_id");
        String name = resultSet.getString("name");
        String lastname = resultSet.getString("lastname");
        return new Author(id, name, lastname);
    }

    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findAllSqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = getAuthor(resultSet);
                authorList.add(author);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return authorList;
    }

    public Author findById(long id) {
        Author author = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByIdSqlQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author = getAuthor(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return author;
    }

    public long save(String name, String lastname) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String property, String value) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSqlQuery);
            preparedStatement.setString(1, property);
            preparedStatement.setString(2, value);
            preparedStatement.setLong(3, id);
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
