package mg.p16.java.connexion;

import java.sql.*;

public class Connexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_avion";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tiafrancky162";
    private static Connection connection;

    public static void connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
