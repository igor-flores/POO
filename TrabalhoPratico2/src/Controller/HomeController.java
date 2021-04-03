package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.*;
import javafx.scene.control.*;

import Model.*;


public class HomeController {
    @FXML private Tab tabFotos, tabFilmes, tabMusicas;
    @FXML private ListView<String> listaFotos, listaFilmes, listaMusicas;
    @FXML private ChoiceBox<String> selectFiltroFilme, selectFiltroMusica;
    @FXML private ChoiceBox<String> ordenacaoFoto, ordenacaoFilme;

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
        } catch (SQLException throwables) {
            exception("Erro: " + throwables.getMessage());
        }
    }

    void setOrdenacao(){
        String[] ordenacoes = {"Código 1-9", "Código 9-1", "Titulo A-Z", "Titulo Z-A", "Data mais recente", "Data mais antiga"};
        ordenacaoFoto.getItems().addAll(ordenacoes);
        ordenacaoFilme.getItems().addAll(ordenacoes);
    }

    @FXML void visualizar(){
        try {
            if(tabFotos.isSelected()){
                String[] split = listaFotos.getSelectionModel().getSelectedItem().split("-");
                ArrayList<String> dados = Foto.readOne(split[0]);

                Main.changeScreen("fotoVisualizar", dados);
            } else if(tabFilmes.isSelected()){
                String[] split = listaFilmes.getSelectionModel().getSelectedItem().split("-");
                Main.changeScreen("filmeVisualizar", Filme.readOne(split[0]));
            }
        } catch (SQLException e){
            exception(e.getMessage());
        } catch (Exception e){
            exception("Você deve selecionar o campo que deseja visualizar");
        }
    }
    @FXML void adicionar() {
        if (tabFotos.isSelected()) Main.changeScreen("fotoCreate");
    }
    @FXML void editar() { }
    @FXML void excluir() {
        try {
            if (tabFotos.isSelected()){
                String[] split = listaFotos.getSelectionModel().getSelectedItem().split("-");
                if(Foto.delete(split[0])){
                    iniciaListas();
                    exception("ok");
                }else {
                    exception("erro");
                }
            } else if(tabFilmes.isSelected()){
                String[] split = listaFilmes.getSelectionModel().getSelectedItem().split("-");
                if(Filme.delete(split[0])){
                    iniciaListas();
                    exception("ok");
                }else {
                    exception("erro");
                }
            }
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
                System.out.println("roi");
            }
        } catch (SQLException throwables) {
            exception(throwables.getMessage());
        }
    }

    void exception(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Puts...");
        alert.setHeaderText(msg);
        alert.show();
    }

    @FXML void btnFiltrarFilme() {}
    @FXML void limparFiltroFilme() {}
    @FXML void btnFiltroMusica() { }

    @FXML void voltar() { Main.changeScreen("Home"); }
}
