package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public static Connection getConnection() {

        Connection myConnection = null;
        String BdD = "cda_final_marc";
        String user;
        String password;
        user = "root";
        password = "";
        String url;

        String OS = System.getProperty("os.name");

        if (OS.contains("Mac")) {
            url = "jdbc:mysql://localhost:3306/" + BdD;
        } else {
            url = "jdbc:mysql://localhost/" + BdD;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion OK");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myConnection;
    }
}

