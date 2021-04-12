package Controller;

import Model.Foto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

public class FotoController extends UtilMidiaController {
    /* READ */
    @FXML private Label fotografo, local, pessoas;

    /* CREATE */
    @FXML private Label dataLabel, localLabel, fotografoLabel, pessoasLabel;
    @FXML private TextField localField, fotografoField, pessoasField;
    @FXML private DatePicker dataField;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "fotoCreate": clearFields(); break;
                case "fotoRead": read(userData); break;
                case "fotoUpdate": initializeUpdate(userData); break;
            }
        });
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                Foto.insert(
                    tituloField.getText(),
                    descricaoField.getText(),
                    selectedFile.getAbsolutePath(),
                    dataField.getValue().toString(),
                    fotografoField.getText(),
                    localField.getText(),
                    pessoasField.getText()
                );
                Main.changeScreen("Home");
                exception("Foto cadastrada com sucesso!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            exception("Poxa, houve algum problema... Revise os campos e tente novamente!");
        }
    }

    void read(ArrayList<String> dados){
        if(fotografo == null) initializeRead();
        super.read(dados);

        fotografo.setText("Fotógrafo: " + dados.get(5));
        local.setText("Local: " + dados.get(6));
        pessoas.setText("Pessoas: " + dados.get(7));
    }

    void initializeRead(){
        super.initializeRead();
        fotografo = new Label();
        pessoas = new Label();
        local = new Label();
    }

    void initializeUpdate(ArrayList<String> dados){
        clearFields();
        idUpdate = Integer.parseInt(dados.get(0));
        tituloField.setText(dados.get(1));
        descricaoField.setText(dados.get(2));
        atualMidia = dados.get(3);
        dataField.setValue(LocalDate.parse(dados.get(4)));
        fotografoField.setText(dados.get(5));
        localField.setText(dados.get(6));
        pessoasField.setText(dados.get(7));
    }

    @FXML void update(){
        if (noError()) {
            String nomeArquivo;
            if(selectedFile != null) nomeArquivo = selectedFile.getAbsolutePath();
            else nomeArquivo = atualMidia;
            try{
                Foto.update(
                    tituloField.getText(),
                    descricaoField.getText(),
                    nomeArquivo,
                    dataField.getValue().toString(),
                    fotografoField.getText(),
                    localField.getText(),
                    pessoasField.getText(),
                    idUpdate
                );

                Main.changeScreen("Home");
                exception("Foto atualizada com sucesso!");
            } catch (ClassNotFoundException | SQLException e) {
                exception(e.getMessage());
            }
        }
    }

    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        if (midiaLabel == null) {
            super.clearFields();
            localLabel = new Label(); fotografoLabel = new Label(); pessoasLabel = new Label();
            localField = new TextField(); fotografoField = new TextField(); pessoasField = new TextField(); dataField = new DatePicker();
        }

        /* SETA / LIMPA TEXTOS */
        localLabel.setText("Local"); fotografoLabel.setText("Fotografo"); pessoasLabel.setText("Pessoas"); alertaBtn.setText("");
        localLabel.setStyle("-fx-text-fill: black"); fotografoLabel.setStyle("-fx-text-fill: black"); pessoasLabel.setStyle("-fx-text-fill: black");
        localField.setText(""); fotografoField.setText(""); pessoasField.setText("");
    }

    @FXML void getFile() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png")
        );

        selectedFile = inputFile.showOpenDialog(null);
        if(selectedFile != null)
            midiaField.setText(selectedFile.getAbsolutePath());

    }

    boolean noError(){
        boolean noError = super.noError();
        if (fotografoField.getText().equals("")){ fotografoLabel.setStyle("-fx-text-fill: red"); noError = false; } else fotografoLabel.setStyle("-fx-text-fill: black");
        if (localField.getText().equals("")){ localLabel.setStyle("-fx-text-fill: red"); noError = false; } else localLabel.setStyle("-fx-text-fill: black");
        if (pessoasField.getText().equals("")){ pessoasLabel.setStyle("-fx-text-fill: red"); noError = false; } else pessoasLabel.setStyle("-fx-text-fill: black");

        try {
            LocalDate.parse(
                dataField.getValue().toString(),
                DateTimeFormatter.ofPattern(
                    "uuuu-M-d"
                ).withResolverStyle(
                    ResolverStyle.STRICT
                )
            );
            dataLabel.setStyle("-fx-text-fill: black");
        } catch (Exception e){
            dataLabel.setStyle("-fx-text-fill: red"); noError = false;
        }

        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }

}
