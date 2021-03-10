package Controller;

import Model.NotaFiscal;
import Model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;

public class NotaFiscalController extends EmpresaController{
    @FXML private AnchorPane panelInputs;
    @FXML private Label alertaNF;
    private HashMap<ChoiceBox<Produto>, TextField> listaItemsAdd;
    private HashMap<CheckBox, TextField> listaItemsEdit;
    private NotaFiscal nf;

    @FXML private ListView<String> listaProdutos;
    @FXML private Label idNF, dataNF;

    @FXML protected void initialize(){
        Main.setListener((newScreen, userData) -> {
            switch (newScreen){
                case "NotaFiscalSelected": {
                    listaItemsAdd = new HashMap<>();
                    NotaFiscal nf = (NotaFiscal) userData;
                    if (idNF == null) idNF = new Label();
                    if (dataNF == null) dataNF = new Label();
                    idNF.setText("Nota Fiscal nº " + nf.getCodigo());
                    dataNF.setText("Data: " + nf.getData());
                    setListaProdutos(nf);
                }break;
                case "NotaFiscalAdd": {
                    listaItemsAdd = new HashMap<>();
                    if (panelInputs == null) panelInputs = new AnchorPane();
                    limpaInputs();
                    addProduto();
                }break;
                case "NotaFiscalEdit": {
                    nf = (NotaFiscal) userData;
                    listaItemsAdd = new HashMap<>();
                    listaItemsEdit = new HashMap<>();
                    if (panelInputs == null) panelInputs = new AnchorPane();
                    limpaInputs();
                    setProdutosEdit(nf);
                    addProduto();
                }break;
            }
            if (minhaEmpresa != null) nomeEmpresa.setText(minhaEmpresa.getNome() + "!");
        });
    }

    /** Botão de adicionar produto
     *  Adiciona campos para colocar mais produtos na Nota Fiscal
     */
    @FXML
    void addProduto() {
        int mult = panelInputs.getChildren().size();

        /* Personaliza inputs */
        ChoiceBox<Produto> selectProduto = new ChoiceBox<>();
        setProdutosSelect(selectProduto);

        TextField quantidade = new TextField();
        quantidade.setPromptText("Qtd.");
        quantidade.setStyle("-fx-pref-width: 75");

        AnchorPane.setLeftAnchor(selectProduto, 14.0);
        AnchorPane.setRightAnchor(selectProduto, 104.0);
        AnchorPane.setTopAnchor(selectProduto, 20.0 * mult);
        AnchorPane.setRightAnchor(quantidade, 14.0);
        AnchorPane.setTopAnchor(quantidade, 20.0 * mult);

        /* Seta inputs na tela */
        panelInputs.getChildren().add(selectProduto);
        panelInputs.getChildren().add(quantidade);
        listaItemsAdd.put(selectProduto, quantidade);
    }

    /** Botão de adicionar NF
     * Procurará por inconsistências, caso não haja adicionará e voltará para a tela Empresa
     */
    @FXML
    void adicionarNF() {
        alertaNF.setText("");

        HashMap<Produto, Double> listaNF = setListaItemsAdd();
        if (listaNF == null) {
            alertaNF.setText("Você não pode inserir mais do que há no estoque :/");
            return;
        }
        if(listaNF.size() == 0){
            alertaNF.setText("Você precisa inserir produtos na nota fiscal para cadastrá-la :/");
            return;
        }
        if(addNotaFiscal(new NotaFiscal(listaNF))) {
            goVoltarEmpresa();
            atualizarListaNFs();
        }
    }


    HashMap<Produto, Double> setListaItemsAdd(){
        HashMap<Produto, Double> listaNF = new HashMap<>();
        try {
            for(ChoiceBox<Produto> select : listaItemsAdd.keySet()){
                TextField quantidade = listaItemsAdd.get(select);
                Produto p = select.getSelectionModel().getSelectedItem();
                if(p != null) {
                    double qtd = Double.parseDouble(quantidade.getText());
                    if(p.getQuantidade() >= qtd){
                        listaNF.put(p, qtd);
                    }else{
                        return null;
                    }
                }
            }
        } catch(Exception e) {
            alertaNF.setText("Confira se todos os campos foram inseridos corretamente!");
        }
        return listaNF;
    }
    @FXML
    void alterarNF() {
        alertaNF.setText("");
        ArrayList<Produto> excluir = new ArrayList<>();
        HashMap<Produto, Double> novosItems = setListaItemsAdd();

        try {
            for (CheckBox cb : listaItemsEdit.keySet()){
                String[] split = cb.getText().split(" - ");
                Produto p = getProduto(Integer.parseInt(split[0]));
                //Se for excluir add no array de exclusão
                if(cb.isSelected()){
                    excluir.add(p);
                }else{
                    double qtdAtualNF = nf.getItems().get(p);
                    addQuantidade(p.getCodigo(), qtdAtualNF);
                    double qtdNova = Double.parseDouble(listaItemsEdit.get(cb).getText());
                    if(subQuantidade(p.getCodigo(), qtdNova)){
                        novosItems.put(p, qtdNova);
                    }else{
                        if(subQuantidade(p.getCodigo(), qtdAtualNF)) {
                            alertaNF.setText("Você não pode inserir mais do que há no estoque :/");
                            return;
                        }
                    }
                }
            }
        } catch(Exception e) {
            alertaNF.setText("Confira se todos os campos foram inseridos corretamente!");
        }
        //Remover produtos da nf
        for(Produto p : excluir) nf.getItems().remove(p);
        nf.getItems().putAll(novosItems);
        if(nf.getItems().isEmpty()) removeNotaFiscal(nf.getCodigo());
        goVoltarEmpresa();
    }

    /**
     * Seta a lista de Produtos da nota Fiscal desejada
     * @param nf = notaFisal selecionada
     */
    void setListaProdutos(NotaFiscal nf){
        if(listaProdutos == null) listaProdutos = new ListView<>();
        listaProdutos.getItems().clear();
        for (Produto p : nf.getItems().keySet()){
            double qtd = nf.getItems().get(p);
            listaProdutos.getItems().add(qtd + "x " +p.getNome() + " - Total: R$" + (qtd * p.getPreco()));
        }
    }


    private void setProdutosEdit(NotaFiscal nf) {
        for(Produto p : nf.getItems().keySet()){
            int mult = panelInputs.getChildren().size();
            /* Personaliza inputs */
            CheckBox checkBox = new CheckBox();
            checkBox.setText(p.getCodigo() + " - " + p.getNome());

            TextField quantidade = new TextField();
            quantidade.setPromptText("Qtd.");
            quantidade.setStyle("-fx-pref-width: 75");
            quantidade.setText(Double.toString(nf.getItems().get(p)));

            AnchorPane.setLeftAnchor(checkBox, 14.0);
            AnchorPane.setTopAnchor(checkBox, 20.0 * mult);
            AnchorPane.setLeftAnchor(quantidade, 180.0);
            AnchorPane.setTopAnchor(quantidade, 20.0 * mult);

            /* Seta inputs na tela */
            panelInputs.getChildren().add(checkBox);
            panelInputs.getChildren().add(quantidade);
            listaItemsEdit.put(checkBox, quantidade);
        }
    }
    /**
     * Colocará produtos dentro do input select
     * @param select = ChoiceBox a ser setado produtos
     */
    void setProdutosSelect(ChoiceBox<Produto> select){
        for(Produto p : minhaEmpresa.getProdutos()){
            if(p.getQuantidade() > 0) {
                select.getItems().add(p);
            }
        }
    }
    void limpaInputs(){
        listaItemsAdd.clear();
        panelInputs.getChildren().clear();
    }

    @Override
    public boolean addNotaFiscal(NotaFiscal nf) {
        for (Produto p : nf.getItems().keySet())
            subQuantidade(p.getCodigo(), nf.getItems().get(p));
        minhaEmpresa.getNotasFiscais().add(nf);
        return true;
    }

    @Override
    public NotaFiscal getNotaFiscal(int codigo) {
        for (NotaFiscal nf : minhaEmpresa.getNotasFiscais()){
            if (nf.getCodigo() == codigo)
                return nf;
        }
        return null;
    }

    @Override
    public double getTotal(int codigo) {
        for(NotaFiscal nf : minhaEmpresa.getNotasFiscais()){
            if (nf.getCodigo() == codigo)
                return nf.valorTotal();
        }
        return -1;
    }

    @Override
    public boolean addItem(int codigo, Produto item) {
        return false;
    }

    @Override
    public boolean removeItem(int codigo, Produto item) {
        return false;
    }
}
