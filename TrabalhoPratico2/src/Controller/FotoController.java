package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class FotoController extends HomeController {
    @FXML private ImageView img;
    @FXML private Label titulo, descricao, fotografo, local, data, pessoas;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            if(newScreen.equals("fotoVisualizar")){
                if(userData != null){
                    view(userData);
                }
            }else if (newScreen.equals("fotoCreate")){

            }
        });
    }

    void view(ArrayList<String> dados){
        if(titulo == null) titulo = new Label();
        if(descricao == null) descricao = new Label();
        if(data == null) data = new Label();
        if(fotografo == null) fotografo = new Label();
        if(pessoas == null) pessoas = new Label();
        if(local == null) local = new Label();
        if(img == null) img = new ImageView();

        titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
        descricao.setText("Descrição: " + dados.get(2));
        data.setText("Data: " + dados.get(4));
        fotografo.setText("Fotógrafo: " + dados.get(5));
        pessoas.setText("Pessoas: " + dados.get(7));
        local.setText("Local: " + dados.get(6));
        img.setImage(new Image("Assets/fotos/" + dados.get(3)));
    }

    @FXML
    void getFilme() {
        FileChooser inputFile = new FileChooser();
        inputFile.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png")
        );

        File selectedFile = inputFile.showOpenDialog(null);

        try{
            File newFile = new File("src/Assets/fotos/" + new Date().getTime() + ".jpg");
            if(selectedFile != null){
                FileUtils.copyFile(
                    selectedFile,
                    newFile
                );
                exception("ok");
            }
        } catch (IOException e) {
            exception(e.getMessage());
        }
    }

}
