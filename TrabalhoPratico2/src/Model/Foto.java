package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Foto extends Connect {
    public static ArrayList<String> readOne(String id) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read (
            "`id_midia`, `titulo`, `descricao`, `caminho_midia`, `data`, `fotografo`, `local`, `pessoas`",
            "id_midia = " + id,
            "1"
        );
        result.next();
        int i = 1;
        while (true){
            try { array.add(result.getString(i)); }
            catch (Exception e){ return array; }
            i++;
        }
    }

    public static ArrayList<String> readAll(String orderBy) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read("id_midia, titulo, data",  "1", orderBy);
        while (result.next()){
            array.add(
                result.getString(1) + " - " +
                result.getString(2) +
                " (" + result.getString(3) + ")"
            );
        }
        return array;
    }
    static ResultSet read(String campos, String where, String orderBy) throws SQLException {
        String sql = "SELECT " + campos + " FROM  `midia` INNER JOIN `foto` ON midia.id_midia = foto.midia_id_midia WHERE " + where + " ORDER BY " + orderBy;
        PreparedStatement prepare = con.prepareStatement(sql);
        return prepare.executeQuery();
    }

    public static boolean delete(String id){
        String sql = "DELETE FROM `midia` WHERE `id_midia` = " + id + "; ";
        try {
            execute(sql);
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            return false;
        }
    }
}
