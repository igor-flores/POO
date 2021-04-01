package Controller;

import java.io.IOException;
import java.util.*;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import Model.*;

public class HomeController {
    @FXML private Tab tabFotos, tabFilmes, tabMusicas;
    @FXML private ListView<Midia> listaFotos, listaFilmes, listaMusicas;
    @FXML private ChoiceBox<String> selectFiltroFilme, selectFiltroMusica;
    @FXML private RadioButton ordenaTituloFoto, ordenaTituloFilme, ordenaTituloMusica;

    /**
     * Inicializa a tela, verificando se a tela é a desejada
     */
    @FXML protected void initialize(){
        selectFiltroFilme.getItems().addAll(Main.banco.getListGenero());
        selectFiltroMusica.getItems().addAll(Main.banco.getListGenero());

        ordenarMidia(getListMidia(Foto.class), true);
        ordenarMidia(getListMidia(Filme.class), true);
        ordenarMidia(getListMidia(Musica.class), true);
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
        ArrayList<Midia> listaFiltro = new ArrayList<>();
        if (tabFotos.isSelected()) {
            listaFiltro = getListMidia(Foto.class);
        } else if (tabFilmes.isSelected()) {
            if (selectFiltroFilme.getValue() != null)
                listaFiltro = ordenarMidia (listaFiltrada(Filme.class, selectFiltroFilme.getValue()), ordenaTituloFilme.isSelected());
            else
                listaFiltro = ordenarMidia (getListMidia(Filme.class), ordenaTituloFilme.isSelected());
        } else if (tabMusicas.isSelected()) {
            if (selectFiltroMusica.getValue() != null)
                listaFiltro = ordenarMidia (listaFiltrada(Musica.class, selectFiltroMusica.getValue()), ordenaTituloFilme.isSelected());
            else
                listaFiltro = ordenarMidia (getListMidia(Musica.class), ordenaTituloMusica.isSelected());
        }
        atualizarLista(listaFiltro);
    }

    @FXML void btnFiltrarFilme() {
        if(selectFiltroFilme.getValue() != null) {
            ArrayList<Midia> listaFiltro = listaFiltrada(Filme.class, selectFiltroFilme.getValue());
            atualizarLista(listaFiltro);
            ordenar();
        }
    }
    @FXML void limparFiltroFilme() {
        selectFiltroFilme.setValue(null);
        ordenar();
    }

    @FXML void btnFiltroMusica() { }

    void inicializaListViews(){
        if(listaFotos == null) listaFotos = new ListView<>();
        if(listaFilmes == null) listaFilmes = new ListView<>();
        if(listaMusicas == null) listaMusicas = new ListView<>();
    }

    void atualizarListas(){
        inicializaListViews();

        listaFotos.getItems().clear();
        listaFilmes.getItems().clear();
        listaMusicas.getItems().clear();

        for(Midia m : Main.banco.getMidias()){
            if (m instanceof Foto)
                listaFotos.getItems().add(m);
            else if (m instanceof Filme)
                listaFilmes.getItems().add(m);
            else
                listaMusicas.getItems().add(m);
        }
    }

    void atualizarLista(ArrayList<Midia> list){
        inicializaListViews();

        if (list.size() > 0) {
            if (list.get(0) instanceof Foto) {
                listaFotos.getItems().clear();
                listaFotos.getItems().addAll(list);
            } else if (list.get(0) instanceof Filme) {
                listaFilmes.getItems().clear();
                listaFilmes.getItems().addAll(list);
            } else if (list.get(0) instanceof Musica) {
                listaMusicas.getItems().clear();
                listaMusicas.getItems().addAll(list);
            }
        }
    }

    ArrayList<Midia> getListMidia(Class midia){
        ArrayList<Midia> list = new ArrayList<>();

        for (Midia m : Main.banco.getMidias()){
            if ((midia == Foto.class) && (m instanceof Foto))
                list.add(m);
            else if ((midia == Filme.class) && (m instanceof Filme))
                list.add(m);
        }

        return list;
    }

    ArrayList<Midia> ordenarMidia(ArrayList<Midia> list, boolean ordenarTitulo){
        if (list.isEmpty()) return null;

        if(ordenarTitulo)
            list.sort(Comparator.comparing(Midia::getTitulo));
        else
            list.sort(Comparator.comparing(Midia::getData));

        return list;
    }

    ArrayList<Midia> listaFiltrada(Class midia, String filtro){
        ArrayList<Midia> listaFiltro = new ArrayList<>();

        for (Midia m : Main.banco.getMidias()){
            if (
                (midia == Filme.class) &&
                (m instanceof MidiaReproducao) &&
                ((MidiaReproducao) m).getGenero().equals(filtro)
            ) listaFiltro.add(m);
        }

        return listaFiltro;
    }
}
