package Controller;

import Model.Connect;
import Model.Filme;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeController extends UtilMidiaReproducaoController {
    @FXML private Label diretor, atores;

    @FXML private Label diretorLabel, atoresLabel;
    @FXML private TextField diretorField, atoresField;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "filmeCreate": clearFields(); break;
                case "filmeRead": read(userData); break;
            }
        });
    }

    void read(ArrayList<String> dados){
        for(String dado: dados)
            System.out.println(dado);

        System.out.println("\n\n\n");
        if (titulo == null) initializeRead();
        super.read(dados);
        diretor.setText("Diretor: " + dados.get(7));
        atores.setText("Atores: " + dados.get(8));
    }
    void initializeRead(){
        super.initializeRead();
        diretor = new Label();
        atores = new Label();
    }

    void clearFields(){
        /* INICIALIZA FIELDS E LABELS */
        if(midiaLabel == null){
            super.clearFields();
            diretorField = new TextField(); atoresField = new TextField();
            diretorLabel = new Label(); atoresLabel = new Label();
        }

        /* SETA / LIMPA TEXTOS */
        diretorLabel.setStyle("-fx-text-fill: black"); atoresLabel.setStyle("-fx-text-fill: black");
        diretorLabel.setText("Diretor"); atoresLabel.setText("Atores");
        diretorField.setText(""); atoresField.setText("");
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                Filme.create(
                    tituloField.getText(),
                    descricaoField.getText(),
                    selectedFile.getAbsolutePath(),
                    anoField.getText(),
                    generoField.getText(),
                    idiomaField.getText(),
                    diretorField.getText(),
                    atoresField.getText()
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
            new FileChooser.ExtensionFilter("Filmes", "*.mp4", "*.mkv", "*.avi")
        );

        selectedFile = inputFile.showOpenDialog(null);
        if(selectedFile != null)
            midiaField.setText(selectedFile.getAbsolutePath());

    }

    boolean noError(){
        boolean noError = super.noError();

        if (atoresField.getText().equals("")){ atoresLabel.setStyle("-fx-text-fill: red"); noError = false; } else atoresLabel.setStyle("-fx-text-fill: black");
        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }
}
