package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Musica extends Connect{
    public static ArrayList<String> readOne(String id) throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read(
                "`id_midia`, `titulo`, `descricao`, `caminho_midia`, Year(data), genero, idioma, interpretes",
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

    public static ArrayList<String> getGeneros() throws SQLException {
        ArrayList<String> array = new ArrayList<>();
        ResultSet result = read("DISTINCT `genero`", "1", "1");
        while (result.next()){
            array.add(result.getString(1));
        }
        return array;
    }

    public static void create(
        String  titulo,
        String  descricao,
        String  nomeArquivo,
        String  data,
        String  genero,
        String  idioma,
        String  interpretes
    ) throws SQLException, ClassNotFoundException {
        Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + titulo + "', '" + descricao + "', '" + nomeArquivo + "', '" + data + "-01-01'); ");
        int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
        Connect.execute("INSERT INTO `midia_reproducao`(genero, idioma, midia_id_midia) VALUES ('" + genero + "', '" + idioma + "', '" + id + "'); ");
        Connect.execute("INSERT INTO `musica`(interpretes, midia_reproducao_id_midia) VALUES ('" + interpretes + "', '" + id + "'); ");

    }
}
