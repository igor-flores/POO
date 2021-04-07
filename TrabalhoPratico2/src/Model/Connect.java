package Model;

import java.sql.*;

public class Connect {
    static java.sql.Connection con;
    static {
        String host = "jdbc:mysql://localhost:3306/banco_midias";
        String root = "root";
        String pswd = "Linux21.";
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection(host, root, pswd);
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
    }

    public static int selectId(String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement prepare = con.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();
        result.next();
        return result.getInt(1);
    }

    public static void query(String sql) throws SQLException, ClassNotFoundException {
        con.prepareStatement(sql).execute();
    }
}
