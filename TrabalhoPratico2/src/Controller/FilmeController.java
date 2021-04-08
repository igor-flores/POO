package Controller;

import Model.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class FilmeController extends HomeController{
    @FXML private Label titulo, descricao, genero, duracao, diretor, ano, atores, idioma;

    @FXML private Label midiaField, midiaLabel, tituloLabel, anoLabel, descricaoLabel, diretorLabel, atoresLabel, idiomaLabel, generoLabel, alertaBtn;
    @FXML private TextField tituloField, diretorField, atoresField, idiomaField, anoField, generoField;
    @FXML private TextArea descricaoField;
    private File selectedFile;

    @FXML private MediaView movie;
    private MediaPlayer mediaPlayer;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "filmeCreate": clearFields(); break;
                case "filmeRead": view(userData); break;
            }
        });
    }

    void view(ArrayList<String> dados){
        try {
            titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
            descricao.setText("Descrição: " + dados.get(2));
            try {
                File file = new File("Assets/filmes/eu_nunca_fiz_isso.mp4");
                String path = file.getAbsolutePath();
                System.out.println(path);
                Media media = new Media(path);
                mediaPlayer = new MediaPlayer(media);
                movie.setMediaPlayer(mediaPlayer);
            }catch (Exception e){ exception(e.getMessage());}
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
            midiaLabel = new Label(); tituloLabel = new Label(); anoLabel = new Label(); descricaoLabel = new Label(); diretorLabel = new Label(); atoresLabel = new Label(); idiomaLabel = new Label(); generoLabel = new Label(); alertaBtn = new Label();
            midiaField = new Label(); tituloField = new TextField(); anoField = new TextField(); descricaoField = new TextArea(); diretorField = new TextField(); atoresField = new TextField(); idiomaField = new TextField(); generoField = new TextField();
        }
        selectedFile = null;
        /* SETA / LIMPA TEXTOS */
        midiaLabel.setText("Midia"); tituloLabel.setText("Titulo"); anoLabel.setText("Ano"); descricaoLabel.setText("Descrição"); diretorLabel.setText("Diretor"); atoresLabel.setText("Atores"); idiomaLabel.setText("Idioma"); generoLabel.setText("Genero"); alertaBtn.setText("");
        midiaLabel.setStyle("-fx-text-fill: black"); tituloLabel.setStyle("-fx-text-fill: black"); anoLabel.setStyle("-fx-text-fill: black"); descricaoLabel.setStyle("-fx-text-fill: black"); diretorLabel.setStyle("-fx-text-fill: black"); atoresLabel.setStyle("-fx-text-fill: black"); idiomaLabel.setStyle("-fx-text-fill: black"); generoLabel.setStyle("-fx-text-fill: black");
        midiaField.setText("Selecionar foto"); tituloField.setText(""); anoField.setText(""); descricaoField.setText(""); diretorField.setText(""); atoresField.setText(""); idiomaField.setText(""); generoField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                String nomeArquivo = new Date().getTime() + ".mp4";
                Connect.query("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + anoField.getText() + "-01-01'); ");
                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
                Connect.query("INSERT INTO `midia_reproducao`(genero, idioma, midia_id_midia) VALUES ('" + generoField.getText() + "', '" + idiomaField.getText() + "', '" + id + "'); ");
                Connect.query("INSERT INTO `filme`(diretor, atores, midia_reproducao_id_midia) VALUES ('" + diretorField.getText() + "', '" + atoresField.getText() + "', '" + id + "'); ");

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

    @FXML void getFile() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Vídeos", "*.mp4", "*.avi", "*.mkv")
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
        if (diretorField.getText().equals("")){ diretorLabel.setStyle("-fx-text-fill: red"); noError = false; } else diretorLabel.setStyle("-fx-text-fill: black");
        if (atoresField.getText().equals("")){ atoresLabel.setStyle("-fx-text-fill: red"); noError = false; } else atoresLabel.setStyle("-fx-text-fill: black");
        if (idiomaField.getText().equals("")){ idiomaLabel.setStyle("-fx-text-fill: red"); noError = false; } else idiomaLabel.setStyle("-fx-text-fill: black");
        if (generoField.getText().equals("")){ generoLabel.setStyle("-fx-text-fill: red"); noError = false; } else generoLabel.setStyle("-fx-text-fill: black");

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
