package Model;

import java.sql.*;

class Connect {

     public static java.sql.Connection getCon(){
        String host = "jdbc:mysql://localhost:3306/banco_midias";
        String root = "root";
        String pswd = "Linux21.";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(host, root, pswd);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
