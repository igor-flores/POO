package Controller;

import Interfaces.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/** Controla tudo referente à empresa, classe mãe de ProdutoController e NotaFiscalController **/
public class EmpresaController implements IProdutos, INotasFiscais {
    /** Guardará a empresa "logada" */
    protected static Empresa minhaEmpresa;

    @FXML protected Label nomeEmpresa;
    @FXML private ListView<Produto> listaProdutos;
    @FXML private ListView<NotaFiscal> listaNFs;

    /**
     * Inicializa a tela, verificando se a tela é a desejada
     */
    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            if(newScreen.equals("Empresa")){
                if (userData != null)
                    minhaEmpresa = (Empresa) userData;
                atualizarListaProdutos();
                atualizarListaNFs();
            }
            if (minhaEmpresa != null) nomeEmpresa.setText(minhaEmpresa.getNome() + "!");
        });
    }

    @FXML void goVoltarEmpresa() { Main.changeScreen("Empresa"); }
    @FXML void efetuarLogout() { Main.changeScreen("Home"); minhaEmpresa = null; }

    /** INICIO CONTROLLER PRODUTOS */
    @FXML void goAdicionarProduto() { Main.changeScreen("ProdutoAdd"); }
    @FXML void goEditarProduto() {
        ObservableList<Produto> ol = listaProdutos.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            Produto p = ol.get(0);
            Main.changeScreen("ProdutoEdit", p);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar");
            alert.setHeaderText("Selecione um produto para editar. ");
            alert.showAndWait();
        }
    }

    @FXML void excluirProduto() {
        ObservableList<Produto> ol = listaProdutos.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            Produto p = ol.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir");
            alert.setHeaderText("Certeza que deseja excluir " + p.getNome() + "?");
            Optional<ButtonType> retorno = alert.showAndWait();
            if(retorno.get() == ButtonType.OK) {
                minhaEmpresa.getProdutos().remove(p);
                atualizarListaProdutos();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excluir");
            alert.setHeaderText("Selecione um produto para excluir. ");
            alert.showAndWait();
        }
    }

    /** FIM CONTROLLER PRODUTOS / INICIO CONTROLLER NOTAS FISCAIS */
    @FXML void goVisualizarNF() {
        ObservableList<NotaFiscal> ol = listaNFs.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            NotaFiscal nf = ol.get(0);
            Main.changeScreen("NotaFiscalSelected", nf);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Visualizar");
            alert.setHeaderText("Selecione uma nota fiscal para visualizar. ");
            alert.showAndWait();
        }
    }
    @FXML void goAdicionarNF() { Main.changeScreen("NotaFiscalAdd"); }
    @FXML void goEditarNF() {
        ObservableList<NotaFiscal> ol = listaNFs.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            NotaFiscal nf = ol.get(0);
            Main.changeScreen("NotaFiscalEdit", nf);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar");
            alert.setHeaderText("Selecione uma nota fiscal para editar. ");
            alert.showAndWait();
        }
    }
    @FXML void excluirNF() {
        ObservableList<NotaFiscal> ol = listaNFs.getSelectionModel().getSelectedItems();
        if(!ol.isEmpty()){
            NotaFiscal nf = ol.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir");
            alert.setHeaderText("Certeza que deseja excluir #" + nf.getCodigo() + "?");
            Optional<ButtonType> retorno = alert.showAndWait();
            if(retorno.get() == ButtonType.OK) {
                if(removeNotaFiscal(nf.getCodigo())){
                    for(Produto p : nf.getItems().keySet())
                        addQuantidade(p.getCodigo(), nf.getItems().get(p));
                }
                atualizarListaNFs();
                atualizarListaProdutos();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excluir");
            alert.setHeaderText("Selecione uma nota fiscal para excluir. ");
            alert.showAndWait();
        }
    }
    /** FIM CONTROLLER NOTAS FISCAIS */

    void atualizarListaProdutos(){
        if(listaProdutos == null) listaProdutos = new ListView<>();
        listaProdutos.getItems().clear();
        for (Produto p : minhaEmpresa.getProdutos())
            listaProdutos.getItems().add(p);
    }

    void atualizarListaNFs(){
        if(listaNFs == null) listaNFs = new ListView<>();
        listaNFs.getItems().clear();
        for (NotaFiscal nf : minhaEmpresa.getNotasFiscais())
            listaNFs.getItems().add(nf);
    }

    /** Implements INotasFiscais */
    public boolean addNotaFiscal(NotaFiscal nf) { return false; }
    public boolean removeNotaFiscal(int codigo) {
        for (NotaFiscal nf : minhaEmpresa.getNotasFiscais()){
            if (nf.getCodigo() == codigo) {
                minhaEmpresa.getNotasFiscais().remove(nf);
                return true;
            }
        }
        return false;
    }
    public NotaFiscal getNotaFiscal(int codigo) { return null; }
    public double getTotal(int codigo) { return 0; }
    public boolean addItem(int codigo, Produto item) { return false; }
    public boolean removeItem(int codigo, Produto item) { return false; }

    /** Implements IProdutos */
    public boolean addProduto(Produto p) { return false; }
    public boolean removeProduto(int codigo) {
        for (Produto p : minhaEmpresa.getProdutos()){
            if(p.getCodigo() == codigo){
                minhaEmpresa.getProdutos().remove(p);
                return true;
            }
        }
        return false;
    }
    public Produto getProduto(int codigo) {
        for(Produto p : minhaEmpresa.getProdutos()){
            if(p.getCodigo() == codigo)
                return p;
        }
        return null;
    }
    public boolean updateQuantidade(int codigo, double nova) { return false; }
    public boolean updatePreco(int codigo, double novo) { return false; }
    public boolean addQuantidade(int codigo, double quantidade) {
        for(Produto p : minhaEmpresa.getProdutos()){
            if(p.getCodigo() == codigo){
                p.setQuantidade(p.getQuantidade() + quantidade);
                return true;
            }
        }
        return false;
    }
    public boolean subQuantidade(int codigo, double quantidade) {
        for(Produto p : minhaEmpresa.getProdutos()){
            if(p.getCodigo() == codigo){
                double valor = p.getQuantidade() - quantidade;
                if(valor < 0) return false;
                p.setQuantidade(valor);
                return true;
            }
        }
        return false;
    }

}
