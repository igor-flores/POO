package Controller;

import Model.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MusicaController extends UtilMidiaReproducaoController {
    @FXML private Label interpretes;

    @FXML private Label interpretesLabel;
    @FXML private TextField interpretesField;
    MediaPlayer mediaPlayer;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "musicaCreate": clearFields(); break;
                case "musicaRead": view(userData); break;
            }
        });
    }

    void view(ArrayList<String> dados){
        if(titulo == null){
            titulo = new Label();
            descricao = new Label();
        }
        try {
            titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
            descricao.setText("Descrição: " + dados.get(2));
            try {
                File file = new File("Assets/musicas/" + dados.get(3));
                Media h = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(h);
                mediaPlayer.play();
            }catch (Exception e){ exception(e.getMessage());}
//            mediaPlayer.play();

        } catch (Exception e){
            Main.changeScreen("Home");
            exception(e.getMessage());
//            exception("Poxa, houve um problema :/");
        }
    }

    void clearFields() {
        super.clearFields();

        /* INICIALIZA FIELDS E LABELS */
        interpretesLabel = new Label(); interpretesField = new TextField();

        /* SETA / LIMPA TEXTOS */
        interpretesLabel.setStyle("-fx-text-fill: black");
        interpretesLabel.setText("Interpretes");
        interpretesField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                String nomeArquivo = new Date().getTime() + ".mp4";
                Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + anoField.getText() + "-01-01'); ");
                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
                Connect.execute("INSERT INTO `midia_reproducao`(genero, idioma, midia_id_midia) VALUES ('" + generoField.getText() + "', '" + idiomaField.getText() + "', '" + id + "'); ");
                Connect.execute("INSERT INTO `musica`(interpretes, midia_reproducao_id_midia) VALUES ('" + interpretesField.getText() + "', '" + id + "'); ");

                if(selectedFile != null){
                    File newFile = new File("src/Assets/musicas/" + nomeArquivo);
                    FileUtils.copyFile(selectedFile, newFile);
                }
                Main.changeScreen("Home");
                exception("Filme cadastrada com sucesso!");
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            exception("Poxa, houve algum problema... Revise os campos e tente novamente!");
        }
    }

    boolean noError(){
        boolean noError = super.noError();

        if (interpretesField.getText().equals("")){ interpretesLabel.setStyle("-fx-text-fill: red"); noError = false; } else interpretesLabel.setStyle("-fx-text-fill: black");
        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
}
