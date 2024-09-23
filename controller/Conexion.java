package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/dietetica";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Se conecto....");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se conecto....");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
