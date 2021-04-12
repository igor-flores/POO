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

    public static void execute(String sql) throws SQLException, ClassNotFoundException {
        con.prepareStatement(sql).execute();
    }

    public static boolean delete(String id){
        String sql = "DELETE FROM `midia` WHERE `id_midia` = " + id + "; ";
        try {
            execute(sql);
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
            return false;
        }
    }
}
