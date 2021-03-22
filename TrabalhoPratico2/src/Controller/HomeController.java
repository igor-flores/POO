package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Model.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HomeController {
    @FXML private Tab tabFotos, tabFilmes, tabMusicas;
    @FXML private ListView<Midia> listaFotos, listaFilmes, listaMusicas;
    @FXML private ChoiceBox<String> selectFiltroFilme, selectFiltroMusica;
    @FXML private RadioButton ordenaTituloFoto, ordenaTituloFilme, ordenaTituloMusica;


    /**
     * Inicializa a tela, verificando se a tela é a desejada
     */
    @FXML protected void initialize(){
        selectFiltroFilme.getItems().addAll(Main.banco.getGeneroFilme());
        selectFiltroMusica.getItems().addAll(Main.banco.getGeneroMusica());
        ordenarMidia(Main.banco.getFotos(), true);
        ordenarMidia(Main.banco.getFilmes(), true);
        ordenarMidia(Main.banco.getMusicas(), true);

        atualizarListas();
    }
    @FXML void visualizar() throws IOException {
        if(tabFotos.isSelected()){
            Stage s1 = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/Visualizar.fxml"));
            Scene scene = new Scene(root);

            s1.setScene(scene);
            s1.show();
        }
    }
    @FXML void adicionar() { }
    @FXML void editar() { }
    @FXML void excluir() { }

    @FXML void ordenar() {
        if (tabFotos.isSelected()) {
            ordenarMidia(Main.banco.getFotos(), ordenaTituloFoto.isSelected());
        }else if (tabFilmes.isSelected()) {
            if(selectFiltroFilme.getValue() != null) {
                ArrayList<Midia> listaFiltro = setaListaFiltro("Filme", selectFiltroFilme.getValue());
                ordenarMidia(listaFiltro, ordenaTituloFilme.isSelected());
                listaFilmes.getItems().clear();
                listaFilmes.getItems().addAll(listaFiltro);
            }else {
                ordenarMidia(Main.banco.getFilmes(), ordenaTituloFilme.isSelected());
            }
        }else if (tabMusicas.isSelected()) {
            if(selectFiltroMusica.getValue() != null) {
                ArrayList<Midia> listaFiltro = setaListaFiltro("Musica", selectFiltroMusica.getValue());
                ordenarMidia(listaFiltro, ordenaTituloMusica.isSelected());
                listaFilmes.getItems().clear();
                listaFilmes.getItems().addAll(listaFiltro);
            }else {
                ordenarMidia(Main.banco.getMusicas(), ordenaTituloMusica.isSelected());
            }
        }
        atualizarListas();
    }
    @FXML void btnFiltroFilme() {
        if(selectFiltroFilme.getValue() != null) {
            ArrayList<Midia> listaFiltro = setaListaFiltro("Filme", selectFiltroFilme.getValue());
            listaFilmes.getItems().clear();
            listaFilmes.getItems().addAll(listaFiltro);
        }
    }
    @FXML void limparFiltroFilme() {
        selectFiltroFilme.setValue(null);
        ordenar();
    }

    @FXML void btnFiltroMusica() {
    }
    void atualizarListas(){
        if(listaFotos == null) listaFotos = new ListView<>();
        if(listaFilmes == null) listaFilmes = new ListView<>();
        if(listaMusicas == null) listaMusicas = new ListView<>();

        listaFotos.getItems().clear();
        listaFilmes.getItems().clear();
        listaMusicas.getItems().clear();

        for (Midia m : Main.banco.getFotos())
            listaFotos.getItems().add(m);

        for (Midia m : Main.banco.getFilmes())
            listaFilmes.getItems().add(m);

    }

    void ordenarMidia(ArrayList<Midia> list, boolean ordenarTitulo){
        if (list.isEmpty()) return;

        if(ordenarTitulo)
            list.sort(Comparator.comparing(Midia::getTitulo));
        else
            list.sort(Comparator.comparing(Midia::getData));

        if(list.get(0) instanceof Foto)
            Main.banco.setFotos(list);
        else if(list.get(0) instanceof Filme)
            Main.banco.setFilmes(list);
        else if(list.get(0) instanceof Musica)
            Main.banco.setMusicas(list);
    }

    ArrayList<Midia> setaListaFiltro(String midia, String filtro){
        ArrayList<Midia> listaFiltro = new ArrayList<>();

        if(midia.equals("Filme")){
            for (Midia m : Main.banco.getFilmes()){
                Filme f = (Filme) m;
                if(f.getGenero().equals(filtro))
                    listaFiltro.add(m);
            }
        }else if (midia.equals("Musica")){
            for (Midia m : Main.banco.getFilmes()){
                Musica musica = (Musica) m;
                if(musica.getGenero().equals(filtro))
                    listaFiltro.add(musica);
            }
        }
        return listaFiltro;
    }

}
