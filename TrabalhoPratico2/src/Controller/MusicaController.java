package Controller;

import Model.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MusicaController extends HomeController{
    @FXML private Label titulo, descricao, genero, duracao, diretor, ano, atores, idioma;

    @FXML private Label midiaField, midiaLabel, tituloLabel, anoLabel, descricaoLabel, interpretesLabel, idiomaLabel, generoLabel, alertaBtn;
    @FXML private TextField tituloField, interpretesField, idiomaField, anoField, generoField;
    @FXML private TextArea descricaoField;
    private File selectedFile;
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
//            try {
//                File file = new File("Assets/musicas/" + dados.get(3));
//                Media h = new Media(file.toURI().toString());
//                mediaPlayer = new MediaPlayer(h);
//                mediaPlayer.play();
//            }catch (Exception e){ exception(e.getMessage());}
//            mediaPlayer.play();

        } catch (Exception e){
            Main.changeScreen("Home");
            exception(e.getMessage());
//            exception("Poxa, houve um problema :/");
        }
    }

    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        if (midiaLabel == null) {
            midiaLabel = new Label(); tituloLabel = new Label(); anoLabel = new Label(); descricaoLabel = new Label(); interpretesLabel = new Label(); idiomaLabel = new Label(); generoLabel = new Label(); alertaBtn = new Label();
            midiaField = new Label(); tituloField = new TextField(); anoField = new TextField(); descricaoField = new TextArea(); interpretesField = new TextField(); idiomaField = new TextField(); generoField = new TextField();
        }
        selectedFile = null;
        /* SETA / LIMPA TEXTOS */
        midiaLabel.setText("Midia"); tituloLabel.setText("Titulo"); anoLabel.setText("Ano"); descricaoLabel.setText("Descrição"); interpretesLabel.setText("Interpretes"); idiomaLabel.setText("Idioma"); generoLabel.setText("Genero"); alertaBtn.setText("");
        midiaLabel.setStyle("-fx-text-fill: black"); tituloLabel.setStyle("-fx-text-fill: black"); anoLabel.setStyle("-fx-text-fill: black"); descricaoLabel.setStyle("-fx-text-fill: black"); interpretesLabel.setStyle("-fx-text-fill: black"); idiomaLabel.setStyle("-fx-text-fill: black"); generoLabel.setStyle("-fx-text-fill: black");
        midiaField.setText("Selecionar música"); tituloField.setText(""); anoField.setText(""); descricaoField.setText(""); interpretesField.setText(""); idiomaField.setText(""); generoField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                String nomeArquivo = new Date().getTime() + ".mp4";
                Connect.query("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + anoField.getText() + "-01-01'); ");
                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
                Connect.query("INSERT INTO `midia_reproducao`(genero, idioma, midia_id_midia) VALUES ('" + generoField.getText() + "', '" + idiomaField.getText() + "', '" + id + "'); ");
                Connect.query("INSERT INTO `musica`(interpretes, midia_reproducao_id_midia) VALUES ('" + interpretesField.getText() + "', '" + id + "'); ");

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

    @FXML void getFile() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Vídeos", "*.mp3")
        );

        selectedFile = inputFile.showOpenDialog(null);
        if(selectedFile != null){
            midiaField.setText(selectedFile.getAbsolutePath());
        }
    }


    boolean noError(){
        boolean noError = true;
        if (tituloField.getText().equals("")){ tituloLabel.setStyle("-fx-text-fill: red"); noError = false; } else tituloLabel.setStyle("-fx-text-fill: black");
        try {
            if(Integer.parseInt(anoField.getText()) > 1000 && Integer.parseInt(anoField.getText()) < 3000){
                anoLabel.setStyle("-fx-text-fill: black");
            }else { throw new Exception(); } // se der ruim vai para a excessão
        } catch (Exception e){
            anoLabel.setStyle("-fx-text-fill: red");
            noError = false;
        }

        if (descricaoField.getText().equals("")) {descricaoLabel.setStyle("-fx-text-fill: red"); noError = false;} else {descricaoLabel.setStyle("-fx-text-fill: black");}
        if (idiomaField.getText().equals("")){ idiomaLabel.setStyle("-fx-text-fill: red"); noError = false; } else idiomaLabel.setStyle("-fx-text-fill: black");
        if (generoField.getText().equals("")){ generoLabel.setStyle("-fx-text-fill: red"); noError = false; } else generoLabel.setStyle("-fx-text-fill: black");

        if (interpretesField.getText().equals("")){ interpretesLabel.setStyle("-fx-text-fill: red"); noError = false; } else interpretesLabel.setStyle("-fx-text-fill: black");
        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
    boolean noErrorAll(){
        boolean noError = noError();
        if (selectedFile == null) { midiaLabel.setStyle("-fx-text-fill: red"); noError = false;} else midiaLabel.setStyle("-fx-text-fill: black");

        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
}
