package com.library.dao;

import com.library.ConnectionPool;
import com.library.dao.interfaces.IRoleDao;
import com.library.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements IRoleDao {
    protected Connection connection;
    protected final Logger logger = LogManager.getLogger("Role Dao");
    protected final String findAllSqlQuery = "select * from " + getTableName() + "";
    protected final String saveSqlQuery = "insert into " + getTableName() + " (role_name)" + " values (?)";
    protected final String updateSqlQuery = "update " + getTableName() + " set role_name = ? where role_id = ?";
    protected final String deleteSqlQuery = "delete from " + getTableName() + " where role_id=?";
    protected final String COLUMN_ID = "role_id";
    protected final String COLUMN_NAME = "role_name";

    public RoleDao() {
        this.connection = ConnectionPool.createConnection();
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.warn(exception);
        }
    }

    String getTableName() {
        return "role";
    }

    private Role getRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        return new Role(id, name);
    }

    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(findAllSqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roleList.add(role);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return roleList;
    }

    public long save(String name) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(saveSqlQuery);
            preparedStatement.setString(1, name);
            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception error) {
            logger.error(error);
        }
        return rowsAffected;
    }

    public long update(long id, String roleName) {
        long rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSqlQuery);
            preparedStatement.setString(1, roleName);
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
