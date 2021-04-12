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

    public static void update(
        String titulo,
        String descricao,
        String nomeArquivo,
        String data,
        String fotografo,
        String local,
        String pessoas,
        int idUpdate
    ) throws SQLException, ClassNotFoundException {
        Connect.execute("UPDATE `midia` SET `titulo` = '"+ titulo +"', `descricao` = '"+ descricao +"', `caminho_midia` = '"+ nomeArquivo +"', `data` = '"+ data +"' WHERE `id_midia` = " + idUpdate);
        Connect.execute("UPDATE `foto` SET `fotografo` = '"+ fotografo +"', `local` = '"+ local +"', `pessoas` = '"+ pessoas +"' WHERE `midia_id_midia` = " + idUpdate);
    }

    public static void insert(
        String titulo,
        String descricao,
        String nomeArquivo,
        String data,
        String fotografo,
        String local,
        String pessoas
    ) throws SQLException, ClassNotFoundException {
        Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + titulo + "', '" + descricao + "', '" + nomeArquivo + "', '" + data + "'); ");
        int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
        Connect.execute("INSERT INTO `foto`(fotografo, local, pessoas, midia_id_midia) VALUES ('" + fotografo + "', '" + local + "', '" + pessoas + "', '" + id + "'); ");
    }

}
