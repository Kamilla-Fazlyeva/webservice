package com.fazlyeva.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    public static Connection getDBConnection() throws SQLException {

        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Specified class cannot be found", e);
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_database",
                    "postgres", "kamilla");
            return dbConnection;

        } catch (SQLException e) {
            logger.error("Connection to database failed", e);

        }
        return dbConnection;
    }
}
