package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;

import java.util.ArrayList;

public class FilmeController extends HomeController{
    @FXML private Label titulo, descricao, genero, duracao, diretor, ano, atores, idioma;
    @FXML private MediaView movie;


    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            if(newScreen.equals("filmeVisualizar")){
                exception("oi");
            }
        });
    }

    void view(ArrayList<String> dados){
        try {
            titulo.setText("#" + dados.get(0) + " - " + dados.get(1));
            descricao.setText("Descrição: " + dados.get(2));
//            ano.setText("Data: " + dados.get(4).split("-")[0]);
//            diretor.setText("Fotógrafo: " + dados.get(5));
//            pessoas.setText("Pessoas: " + dados.get(7));
//            local.setText("Local: " + dados.get(6));
//            movie.setMediaPlayer(
//                new MediaPlayer(
//                    new Media(
//                        new File(
//                            "images/filmes/" + dados.get(3)
//                        ).toURI().toString()
//                    )
//                )
//            );
        } catch (Exception e){
            Main.changeScreen("Home");
            exception(e.getMessage());
//            exception("Poxa, houve um problema :/");
        }
    }

}
