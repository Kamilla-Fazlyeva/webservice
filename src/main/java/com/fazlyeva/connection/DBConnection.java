package com.fazlyeva.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fazlyeva.enums.WebServiceConstants.Database.*;
import static com.fazlyeva.enums.WebServiceConstants.LoggerMessages.*;
import static com.fazlyeva.utils.PropertiesLoader.getProperties;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    public static Connection getDBConnection() throws SQLException {

        Connection dbConnection = null;
        try {
            Class.forName(getProperties().getProperty(DB_DRIVER.value));
            logger.info(INFO_DB_DRIVER.value);
        } catch (ClassNotFoundException | IOException e) {
            logger.error(ERROR_DB_DRIVER.value, e);
        }
        try {
            dbConnection = DriverManager.getConnection(getProperties().getProperty(DB_URL.value),
                    getProperties().getProperty(DB_LOGIN.value),
                    getProperties().getProperty(DB_PASSWORD.value));
            logger.info(INFO_DB_CONNECTION.value);
            return dbConnection;

        } catch (SQLException | IOException e) {
            logger.error(ERROR_SQL.value, e);

        }

        return dbConnection;
    }
}
