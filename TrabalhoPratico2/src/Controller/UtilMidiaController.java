package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

class UtilMidiaController {
    @FXML protected Label titulo, descricao, caminhoMidia;
    @FXML protected Label midiaField, midiaLabel, tituloLabel, descricaoLabel, alertaBtn;
    @FXML protected TextField tituloField;
    @FXML protected TextArea descricaoField;
    protected File selectedFile;
    protected String atualMidia;
    protected int idUpdate;

    @FXML void voltar() { Main.changeScreen("Home"); }

    @FXML void getFile() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png")
        );

        selectedFile = inputFile.showOpenDialog(null);
        if(selectedFile != null){
            midiaField.setText(selectedFile.getAbsolutePath());
        }
    }

    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        midiaLabel = new Label(); tituloLabel = new Label(); descricaoLabel = new Label(); alertaBtn = new Label();
        midiaField = new Label(); tituloField = new TextField();  descricaoField = new TextArea();

        selectedFile = null;

        /* SETA / LIMPA TEXTOS */
        midiaLabel.setText("Midia"); tituloLabel.setText("Titulo");  descricaoLabel.setText("Descrição"); alertaBtn.setText("");
        midiaLabel.setStyle("-fx-text-fill: black"); tituloLabel.setStyle("-fx-text-fill: black"); descricaoLabel.setStyle("-fx-text-fill: black");
        midiaField.setText("Selecionar foto"); tituloField.setText(""); descricaoField.setText("");
    }
    boolean noError(){
        boolean noError = true;
        if (tituloField.getText().equals("")){ tituloLabel.setStyle("-fx-text-fill: red"); noError = false; } else tituloLabel.setStyle("-fx-text-fill: black");
        if (descricaoField.getText().equals("")) {descricaoLabel.setStyle("-fx-text-fill: red"); noError = false;} else {descricaoLabel.setStyle("-fx-text-fill: black");}

        return noError;
    }

    boolean noErrorAll(){
        boolean noError = noError();
        if (selectedFile == null) { midiaLabel.setStyle("-fx-text-fill: red"); noError = false;} else midiaLabel.setStyle("-fx-text-fill: black");

        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }

    static void exception(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Puts...");
        alert.setHeaderText(msg);
        alert.show();
    }
}

class UtilMidiaReproducaoController extends UtilMidiaController {
    @FXML protected Label ano, genero, idioma;
    @FXML protected Label anoLabel, idiomaLabel, generoLabel;
    @FXML protected TextField idiomaField, anoField, generoField;

    boolean noError(){
        boolean noError = super.noError();
        try {
            if(Integer.parseInt(anoField.getText()) > 1000 && Integer.parseInt(anoField.getText()) < 3000){
                anoLabel.setStyle("-fx-text-fill: black");
            }else { throw new Exception(); } // se der ruim vai para a excessão
        } catch (Exception e){
            anoLabel.setStyle("-fx-text-fill: red");
            noError = false;
        }

        if (idiomaField.getText().equals("")){ idiomaLabel.setStyle("-fx-text-fill: red"); noError = false; } else idiomaLabel.setStyle("-fx-text-fill: black");
        if (generoField.getText().equals("")){ generoLabel.setStyle("-fx-text-fill: red"); noError = false; } else generoLabel.setStyle("-fx-text-fill: black");

        return noError;
    }
    void clearFields(){
        super.clearFields();

        /* INICIALIZA FIELDS E LABELS */
        anoField = new TextField(); idiomaField = new TextField(); generoField = new TextField();
        anoLabel = new Label(); idiomaLabel = new Label(); generoLabel = new Label();

        /* SETA / LIMPA TEXTOS */
        anoLabel.setStyle("-fx-text-fill: black"); idiomaLabel.setStyle("-fx-text-fill: black"); generoLabel.setStyle("-fx-text-fill: black");
        anoLabel.setText("Ano"); idiomaLabel.setText("Idioma"); generoLabel.setText("Genero");
        anoField.setText(""); idiomaField.setText(""); generoField.setText("");
    }
}