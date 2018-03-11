package MyStore.Libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
    private String username;
    private String password;
    private String database;

    private Connection connection;

    public MyConnection() {
        this.username = "masterpuntoventa";
        this.password = "master";
        this.database = "puntoventa_db";
    }

    public boolean setConnection() {

        boolean retval = false;

        this.connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.database, this.username, this.password);
            System.out.println("Conexión establecida con la base de datos");
            retval = true;
        } catch (Exception e) {
            System.out.println("No se ha podido establecer la conexión con la base de datos: " + e.getMessage());
        }
        return retval;
    }

    public boolean closeConnection() {

        boolean retval = false;
        try {
            this.connection.close();
            System.out.println("Se ha cerrado la conexión a la base de datos.");
            retval = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public boolean execQuery(String query) {

        boolean retval = false;

        try {
            Statement stmt = this.connection.createStatement();

            stmt.execute(query);

            retval = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public Connection getConnection() {
        return connection;
    }
}
