package Controller;

import Model.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FilmeController extends UtilMidiaReproducaoController {
    @FXML private Label diretor, atores;

    @FXML private Label diretorLabel, atoresLabel;
    @FXML private TextField diretorField, atoresField;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "filmeCreate": clearFields(); break;
                case "filmeRead": view(userData); break;
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
//            try {
//                File file = new File("Assets/filmes/" + dados.get(3));
//                String path = file.getAbsolutePath();
//                media = new Media(new File(path).toURI().toString());
//                mediaPlayer = new MediaPlayer(media);
//                movie.setMediaPlayer(mediaPlayer);
//
//                mediaPlayer.play();
//            }catch (Exception e){
//                exception(e.getMessage());
//            }


        } catch (Exception e){
//            Main.changeScreen("Home");
//            exception(e.getMessage());
//            exception("Poxa, houve um problema :/");
        }
    }

    void clearFields(){
        super.clearFields();

        /* INICIALIZA FIELDS E LABELS */
        diretorField = new TextField(); atoresField = new TextField();
        diretorLabel = new Label(); atoresLabel = new Label();

        /* SETA / LIMPA TEXTOS */
        diretorLabel.setStyle("-fx-text-fill: black"); atoresLabel.setStyle("-fx-text-fill: black");
        diretorLabel.setText("Diretor"); atoresLabel.setText("Atores");
        diretorField.setText(""); atoresField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                String nomeArquivo = new Date().getTime() + ".mp4";
                Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + anoField.getText() + "-01-01'); ");
                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
                Connect.execute("INSERT INTO `midia_reproducao`(genero, idioma, midia_id_midia) VALUES ('" + generoField.getText() + "', '" + idiomaField.getText() + "', '" + id + "'); ");
                Connect.execute("INSERT INTO `filme`(diretor, atores, midia_reproducao_id_midia) VALUES ('" + diretorField.getText() + "', '" + atoresField.getText() + "', '" + id + "'); ");

                if(selectedFile != null){
                    File newFile = new File("src/Assets/filmes/" + nomeArquivo);
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

        if (atoresField.getText().equals("")){ atoresLabel.setStyle("-fx-text-fill: red"); noError = false; } else atoresLabel.setStyle("-fx-text-fill: black");
        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
}
