package Controller;

import Model.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

public class FotoController extends UtilMidiaController {
    /** READ */
    @FXML private ImageView img;
    @FXML private Label fotografo, local, data, pessoas;

    /** CREATE */
    @FXML private Label dataLabel, localLabel, fotografoLabel, pessoasLabel;
    @FXML private TextField localField, fotografoField, pessoasField;
    @FXML private DatePicker dataField;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "fotoCreate": clearFields(); break;
                case "fotoRead": read(userData); break;
                case "fotoUpdate": InitializeUpdate(userData); break;
            }
        });
    }

    @FXML void create() {
        try {
            if (noErrorAll()) {
                String nomeArquivo = new Date().getTime() + ".jpg";
                Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + dataField.getValue() + "'); ");
                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
                Connect.execute("INSERT INTO `foto`(fotografo, local, pessoas, midia_id_midia) VALUES ('" + fotografoField.getText() + "', '" + localField.getText() + "', '" + pessoasField.getText() + "', '" + id + "'); ");

                if(selectedFile != null){
                    File newFile = new File("src/Assets/fotos/" + nomeArquivo);
                    FileUtils.copyFile(selectedFile, newFile);
                }
                Main.changeScreen("Home");
                exception("Foto cadastrada com sucesso!");
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            exception("Poxa, houve algum problema... Revise os campos e tente novamente!");
        }
    }

    void read(ArrayList<String> dados){
        if(titulo == null){
            titulo = new Label();
            descricao = new Label();
            data = new Label();
            fotografo = new Label();
            pessoas = new Label();
            local = new Label();
            img = new ImageView();
        }

        titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
        descricao.setText("Descrição: " + dados.get(2));
        data.setText("Data: " + dados.get(4));
        fotografo.setText("Fotógrafo: " + dados.get(5));
        pessoas.setText("Pessoas: " + dados.get(7));
        local.setText("Local: " + dados.get(6));
        img.setImage(new Image("Assets/fotos/" + dados.get(3)));
    }

    void InitializeUpdate(ArrayList<String> dados){
        clearFields();
        tituloField.setText(dados.get(1));
        descricaoField.setText(dados.get(2));
        dataField.setValue(LocalDate.parse(dados.get(4)));
        fotografoField.setText(dados.get(5));
        localField.setText(dados.get(6));
        pessoasField.setText(dados.get(7));
    }

    @FXML void update(){
        try{
            if (noError()) {
                String nomeArquivo;

                if(selectedFile != null){
                    nomeArquivo = new Date().getTime() + ".jpg";
                    File newFile = new File("src/Assets/fotos/" + nomeArquivo);
                    FileUtils.copyFile(selectedFile, newFile);
                }

//                String nomeArquivo = new Date().getTime() + ".jpg";
//                Connect.execute("INSERT INTO `midia`(`titulo`, `descricao`, `caminho_midia`, `data`) VALUES ('" + tituloField.getText() + "', '" + descricaoField.getText() + "', '" + nomeArquivo + "', '" + dataField.getValue() + "'); ");
//                int id = Connect.selectId("SELECT MAX(id_midia) FROM midia");
//                Connect.execute("INSERT INTO `foto`(fotografo, local, pessoas, midia_id_midia) VALUES ('" + fotografoField.getText() + "', '" + localField.getText() + "', '" + pessoasField.getText() + "', '" + id + "'); ");
//
//                if(selectedFile != null){
//                    File newFile = new File("src/Assets/fotos/" + nomeArquivo);
//                    FileUtils.copyFile(selectedFile, newFile);
//                }
//                Main.changeScreen("Home");
                exception("Foto cadastrada com sucesso!");
            }
//        } catch (IOException | ClassNotFoundException | SQLException e) {
        } catch (IOException e) {
            exception(e.getMessage());
        }
    }


    void clearFields() {
        /* INICIALIZA FIELDS E LABELS */
        if (midiaLabel == null) {
            midiaLabel = new Label(); tituloLabel = new Label(); dataLabel = new Label(); descricaoLabel = new Label(); localLabel = new Label(); fotografoLabel = new Label(); pessoasLabel = new Label(); alertaBtn = new Label();
            midiaField = new Label(); tituloField = new TextField(); dataField = new DatePicker(); descricaoField = new TextArea(); localField = new TextField(); fotografoField = new TextField(); pessoasField = new TextField();
        }

        /* SETA / LIMPA TEXTOS */
        selectedFile = null;
        midiaLabel.setText("Midia"); tituloLabel.setText("Titulo"); dataLabel.setText("Data"); descricaoLabel.setText("Descrição: "); localLabel.setText("Local"); fotografoLabel.setText("Fotografo"); pessoasLabel.setText("Pessoas"); alertaBtn.setText("");
        midiaLabel.setStyle("-fx-text-fill: black"); tituloLabel.setStyle("-fx-text-fill: black"); dataLabel.setStyle("-fx-text-fill: black"); descricaoLabel.setStyle("-fx-text-fill: black"); localLabel.setStyle("-fx-text-fill: black"); fotografoLabel.setStyle("-fx-text-fill: black"); pessoasLabel.setStyle("-fx-text-fill: black");
        midiaField.setText("Selecionar foto"); tituloField.setText(""); dataField.setValue(null); descricaoField.setText(""); localField.setText(""); fotografoField.setText(""); pessoasField.setText("");
    }

    boolean noError(){
        boolean noError = super.noError();
        if (fotografoField.getText().equals("")){ fotografoLabel.setStyle("-fx-text-fill: red"); noError = false; } else fotografoLabel.setStyle("-fx-text-fill: black");
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
        if (localField.getText().equals("")){ localLabel.setStyle("-fx-text-fill: red"); noError = false; } else localLabel.setStyle("-fx-text-fill: black");
        if (pessoasField.getText().equals("")){ pessoasLabel.setStyle("-fx-text-fill: red"); noError = false; } else pessoasLabel.setStyle("-fx-text-fill: black");

        if (!noError) alertaBtn.setText("Preencha todos os campos corretamente.");
        else alertaBtn.setText("");

        return noError;
    }

}
