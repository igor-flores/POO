package Model;

import Model.Midia;

import java.sql.*;
import java.util.ArrayList;

class Connect {
    private java.sql.Connection con = null;

    Connect(){
        String host = "jdbc:mysql://localhost:3306/banco_midias";
        String root = "root";
        String pswd = "Linux21.";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, root, pswd);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Midia> getMidias(Class midia) throws SQLException {
        PreparedStatement prepare = null;
        prepare = con.prepareStatement("SELECT * FROM `midia`");
        ResultSet result = prepare.executeQuery();
        while (result.next()){
            System.out.println(result.getString(0));
        }
        return null;
    }
}
