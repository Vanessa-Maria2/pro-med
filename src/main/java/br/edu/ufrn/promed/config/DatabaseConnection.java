package br.edu.ufrn.promed.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseConnection {

    private Connection connection;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    public Connection getConnection() throws SQLException {
        return connection = DriverManager.getConnection(url, username, password);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
