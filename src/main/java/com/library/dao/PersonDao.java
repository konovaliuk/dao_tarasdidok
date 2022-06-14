package com.library.dao;

import com.library.ConnectionPool;
import com.library.dao.interfaces.IPersonDao;
import com.library.entities.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements IPersonDao {
    protected Connection connection = null;
    protected final Logger logger = LogManager.getLogger("Person Dao");
    protected final String findAllSqlQuery = "select * from " + getTableName() + "";
    protected final String findByIdSqlQuery = "select * from " + getTableName() + " where person_id=?";
    protected final String findByPhoneSqlQuery = "select * from " + getTableName() + " where phone=?";
    protected final String saveSqlQuery = "insert into " + getTableName() +
            " (person_role, name, lastname, email, phone)" + " values (?, ?, ?, ?, ?)";
    protected final String deleteSqlQuery = "delete from " + getTableName() + " where person_id=?";

    public PersonDao() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = ConnectionPool.createConnection();
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.warn(exception);
        }
    }

    String getTableName() {
        return "person";
    }

    private Person getPerson(ResultSet resultSet) throws SQLException {
        long personId = resultSet.getLong("person_id");
        long personRole = resultSet.getLong("person_role");
        String name = resultSet.getString("name");
        String lastname = resultSet.getString("lastname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        return new Person(personId, personRole, name, lastname, email, phone);
    }

    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findAllSqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = getPerson(resultSet);
                personList.add(person);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return personList;
    }

    public Person findById(long id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByIdSqlQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person = getPerson(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return person;
    }

    public Person findByPhone(String phone) {
        Person person = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findByPhoneSqlQuery);
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person = getPerson(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return person;
    }

    public long save(long personRole, String name, String lastname, String email, String phone) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setLong(1, personRole);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String property, String value) {
        final String updateSqlQuery = "update " + getTableName() + " set " + property + " = ? where person_id = ?";
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
