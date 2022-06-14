package com.library;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionPool extends BasicDataSource {
    private static ConnectionPool instance = null;
    private static final Logger logger = LogManager.getLogger("ConnectionPool");

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) instance = new ConnectionPool();
        return instance;
    }

    public static Properties getProperties() {
        Properties appProperties = new Properties();
        try {
            FileInputStream propertiesFile = new FileInputStream("src/main/resources/application.properties");
            appProperties.load(propertiesFile);
            propertiesFile.close();
        } catch (Exception exception) {
            logger.error(exception);
        }
        return appProperties;
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Properties applicationProperties = getProperties();
            connectionPool.setUrl(applicationProperties.getProperty("url"));
            connectionPool.setUsername(applicationProperties.getProperty("mysqlUsername"));
            connectionPool.setPassword(applicationProperties.getProperty("mysqlPassword"));
            connection = connectionPool.getConnection();
        } catch (Exception exception) {
            logger.error(exception);
        }
        return connection;
    }

}
