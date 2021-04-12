package Controller;

import Model.Musica;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.sql.SQLException;
import java.util.ArrayList;

public class MusicaController extends UtilMidiaReproducaoController {
    @FXML private Label interpretes;

    @FXML private Label interpretesLabel;
    @FXML private TextField interpretesField;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "musicaCreate": clearFields(); break;
                case "musicaRead": view(userData); break;
            }
        });
    }

    void view(ArrayList<String> dados){
        if(interpretes == null) initializeRead();
        super.read(dados);
        interpretes.setText("Intérpretes: ");
    }

    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        if (midiaLabel == null) {
            super.clearFields();
            interpretesLabel = new Label(); interpretesField = new TextField();
        }

        /* SETA / LIMPA TEXTOS */
        interpretesLabel.setStyle("-fx-text-fill: black");
        interpretesLabel.setText("Interpretes");
        interpretesField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                Musica.create(
                    tituloField.getText(),
                    descricaoField.getText(),
                    selectedFile.getAbsolutePath(),
                    anoField.getText(),
                    generoField.getText(),
                    idiomaField.getText(),
                    interpretesField.getText()
                );
                Main.changeScreen("Home");
                exception("Filme cadastrada com sucesso!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            exception("Poxa, houve algum problema... Revise os campos e tente novamente!");
        }
    }

    @FXML void getFile() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Músicas", "*.mp3")
        );

        selectedFile = inputFile.showOpenDialog(null);
        if(selectedFile != null)
            midiaField.setText(selectedFile.getAbsolutePath());

    }
    boolean noError(){
        boolean noError = super.noError();

        if (interpretesField.getText().equals("")){ interpretesLabel.setStyle("-fx-text-fill: red"); noError = false; } else interpretesLabel.setStyle("-fx-text-fill: black");
        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
}
