package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:postgresql://localhost:5433/asur";
    private static final String USER = "mateo";
    private static final String PASSWORD = "*********";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    }
