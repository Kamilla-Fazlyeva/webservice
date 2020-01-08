package com.fazlyeva.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersonDB {

    public static Connection getDBConnection() throws SQLException {

        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_database",
                    "postgres", "kamilla");
            return dbConnection;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return dbConnection;
    }
}
