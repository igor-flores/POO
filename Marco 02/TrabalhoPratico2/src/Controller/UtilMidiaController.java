package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;

class UtilMidiaController {
    @FXML protected Label titulo, descricao, caminhoMidia, data;
    @FXML protected Label midiaField, midiaLabel, tituloLabel, descricaoLabel, alertaBtn;
    @FXML protected TextField tituloField;
    @FXML protected TextArea descricaoField;
    protected File selectedFile;
    protected String atualMidia;
    protected int idUpdate;

    @FXML void voltar() { Main.changeScreen("Home"); }

    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        midiaLabel = new Label(); tituloLabel = new Label(); descricaoLabel = new Label(); alertaBtn = new Label();
        midiaField = new Label(); tituloField = new TextField(); descricaoField = new TextArea();

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

    void initializeRead(){
        titulo = new Label();
        descricao = new Label();
        caminhoMidia = new Label();
        data = new Label();
    }

    void read(ArrayList<String> dados){
        titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
        descricao.setText("Descrição: " + dados.get(2));
        caminhoMidia.setText("Caminho da Midia: " + dados.get(3));
        data.setText("Data / Ano: " + dados.get(4));
    }

    static void exception(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Puts...");
        alert.setHeaderText(msg);
        alert.show();
    }
}

class UtilMidiaReproducaoController extends UtilMidiaController {
    @FXML protected Label genero, idioma, duracao;
    @FXML protected Label anoLabel, idiomaLabel, generoLabel;
    @FXML protected TextField idiomaField, anoField, generoField;

    void initializeRead(){
        super.initializeRead();
        genero = new Label();
        idioma = new Label();
        duracao = new Label();
    }

    void read(ArrayList<String> dados){
        super.read(dados);
        genero.setText("Genero: " + dados.get(6));
        idioma.setText("Idioma: " + dados.get(7));
        duracao.setText("Idioma: " + dados.get(7));
    }
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