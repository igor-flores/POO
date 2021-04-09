package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.*;
import javafx.scene.control.*;

import Model.*;


public class HomeController extends UtilMidiaController {
    @FXML private Tab tabFotos, tabFilmes, tabMusicas;
    @FXML private ListView<String> listaFotos, listaFilmes, listaMusicas;
    @FXML private ChoiceBox<String> filtroFilme, filtroMusica;
    @FXML private ChoiceBox<String> ordenacaoFoto, ordenacaoFilme, ordenacaoMusica;

    @FXML protected void initialize(){
        iniciaListas();
        setOrdenacao();
    }
    void inicializaListViews(){
        if(listaFotos == null) listaFotos = new ListView<>();
        if(listaFilmes == null) listaFilmes = new ListView<>();
        if(listaMusicas == null) listaMusicas = new ListView<>();
    }
    void iniciaListas(){
        inicializaListViews();
        listaFotos.getItems().clear();
        listaFilmes.getItems().clear();
        listaMusicas.getItems().clear();
        try {
            listaFotos.getItems().addAll(Foto.readAll("id_midia"));
            listaFilmes.getItems().addAll(Filme.readAll("id_midia"));
            listaMusicas.getItems().addAll(Musica.readAll("id_midia"));
        } catch (SQLException e) {
            exception("Erro: " + e.getMessage());
        }
    }

    void setOrdenacao(){
        String[] ordenacoes = {"Código 1-9", "Código 9-1", "Titulo A-Z", "Titulo Z-A", "Data mais recente", "Data mais antiga"};
        ordenacaoFoto.getItems().addAll(ordenacoes);
        ordenacaoFilme.getItems().addAll(ordenacoes);
        ordenacaoMusica.getItems().addAll(ordenacoes);
        try {
          filtroMusica.getItems().addAll(Musica.getGeneros());
          filtroFilme.getItems().addAll(Filme.getGeneros());
        } catch (SQLException e) {
            exception(e.getMessage());
        }
    }

    @FXML void visualizar(){
        try {
            if(tabFotos.isSelected()){
                String[] split = listaFotos.getSelectionModel().getSelectedItem().split("-");
                Main.changeScreen("fotoRead", Foto.readOne(split[0]));
            } else if(tabFilmes.isSelected()){
                String[] split = listaFilmes.getSelectionModel().getSelectedItem().split("-");
                Main.changeScreen("filmeRead", Filme.readOne(split[0]));
            } else if(tabMusicas.isSelected()){
                String[] split = listaMusicas.getSelectionModel().getSelectedItem().split("-");
                Main.changeScreen("musicaRead", Musica.readOne(split[0]));
            }
        } catch (Exception e){
            exception(e.getMessage());
        }//            exception("Você deve selecionar o campo que deseja visualizar");

    }
    @FXML void adicionar() {
        if (tabFotos.isSelected()) Main.changeScreen("fotoCreate");
        else if (tabFilmes.isSelected()) Main.changeScreen("filmeCreate");
        else if (tabMusicas.isSelected()) Main.changeScreen("musicaCreate");
    }
    @FXML void editar() {
        try {
            if(tabFotos.isSelected()){
                String[] split = listaFotos.getSelectionModel().getSelectedItem().split("-");
                ArrayList<String> dados = Foto.readOne(split[0]);

                Main.changeScreen("fotoUpdate", dados);
            } else if(tabFilmes.isSelected()){
                String[] split = listaFilmes.getSelectionModel().getSelectedItem().split("-");
                Main.changeScreen("filmeRead", Filme.readOne(split[0]));
            }
        } catch (Exception e){
            exception(e.getMessage());
        }//            exception("Você deve selecionar o campo que deseja editar");

    }
    @FXML void excluir() {
        try {
            if (tabFotos.isSelected()){
                String[] split = listaFotos.getSelectionModel().getSelectedItem().split("-");
                if(Foto.delete(split[0])) exception("ok");
                else exception("erro");
            } else if(tabFilmes.isSelected()){
                String[] split = listaFilmes.getSelectionModel().getSelectedItem().split("-");
                if(Filme.delete(split[0])) exception("ok");
                else exception("erro");

            } else if(tabMusicas.isSelected()){
                String[] split = listaMusicas.getSelectionModel().getSelectedItem().split("-");
                if(Musica.delete(split[0])) exception("ok");
                else exception("erro");
            }
            iniciaListas();
        } catch (Exception e){
            exception(e.getMessage());
        }
    }

    @FXML void buscarFilme() {}
    @FXML void buscarFoto() {
        try {
            if (tabFotos.isSelected()) {
                listaFotos.getItems().clear();
                switch (ordenacaoFoto.getValue()) {
                    case "Titulo A-Z": listaFotos.getItems().addAll(Foto.readAll("titulo ASC")); break;
                    case "Titulo Z-A": listaFotos.getItems().addAll(Foto.readAll("titulo DESC")); break;
                    case "Data mais recente": listaFotos.getItems().addAll(Foto.readAll("data DESC")); break;
                    case "Data mais antiga": listaFotos.getItems().addAll(Foto.readAll("data ASC")); break;
                    case "Código 9-1": listaFotos.getItems().addAll(Foto.readAll("id_midia DESC")); break;
                    default: listaFotos.getItems().addAll(Foto.readAll("id_midia ASC")); break;
                }
            } else if(tabFilmes.isSelected()){
                listaFilmes.getItems().clear();
                switch (ordenacaoFoto.getValue()) {
                    case "Titulo A-Z": listaFilmes.getItems().addAll(Filme.readAll("titulo ASC")); break;
                    case "Titulo Z-A": listaFilmes.getItems().addAll(Filme.readAll("titulo DESC")); break;
                    case "Data mais recente": listaFilmes.getItems().addAll(Filme.readAll("data DESC")); break;
                    case "Data mais antiga": listaFilmes.getItems().addAll(Filme.readAll("data ASC")); break;
                    case "Código 9-1": listaFilmes.getItems().addAll(Filme.readAll("id_midia DESC")); break;
                    default: listaFilmes.getItems().addAll(Filme.readAll("id_midia ASC")); break;
                }
            }
        } catch (SQLException throwables) {
            exception(throwables.getMessage());
        }
    }

    @FXML void btnFiltrarFilme() {}
    @FXML void limparFiltroFilme() {}
    @FXML void btnFiltroMusica() { }

}
