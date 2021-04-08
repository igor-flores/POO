package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Musica extends Connect{
    public static ArrayList<String> readOne(String id) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read(
            "`id_midia`, `titulo`, `descricao`, `caminho_midia`",
            "id_midia = " + id,
            "1"
        );
        result.next();
        int i = 1;
        while (true){
            try {
                array.add(result.getString(i));
            } catch (Exception e){
                return array;
            }
            i++;
        }

    }

    public static ArrayList<String> readAll(String orderBy) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read("id_midia, titulo, data",  "1", orderBy);

        while (result.next()){
            String[] splitData = result.getString(3).split("-");
            array.add(
                result.getString(1) + " - " +
                result.getString(2) +
                " (" + splitData[0]  + ")"
            );
        }
        return array;
    }
    static ResultSet read(String campos, String where, String orderBy) throws SQLException {
        String sql = ("SELECT "+ campos +" FROM `musica` INNER JOIN `midia_reproducao` ON `musica`.`midia_reproducao_id_midia` = `midia_reproducao`.`midia_id_midia` INNER JOIN `midia` ON `midia`.`id_midia`=`musica`.`midia_reproducao_id_midia` WHERE " + where + " ORDER BY " + orderBy);
        assert con != null;
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

    public static ArrayList<String> getGeneros(){

    }
}
