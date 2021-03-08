package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.util.HashMap;

public class NotaFiscalController extends EmpresaController{
    @FXML private AnchorPane panelInputs;
    @FXML private ChoiceBox<Produto> produtoInicial;
    @FXML private Label alertaNF;
    @FXML private TextField qtdInicial;
    private static final HashMap<ChoiceBox<Produto>, TextField> listaItems = new HashMap<>();

    @FXML protected void initialize(){
        Main.setListener(new Main.OnChangeScreen() {
            @Override
            public void screenChanged(String newScreen, Object userData) {
                if(newScreen.equals("NotaFiscalAdd")){
                    limpaInputs();
                    setProdutosSelect(produtoInicial);
                    listaItems.put(produtoInicial, qtdInicial);
                }
                if (minhaEmpresa != null) nomeEmpresa.setText(minhaEmpresa.getNome() + "!");
            }
        });
    }

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
        listaItems.put(selectProduto, quantidade);
    }

    @FXML
    void adicionarNF() {
        alertaNF.setText("");
        HashMap<Produto, Integer> listaNF = new HashMap<>();
        boolean erro = false;
        try {
            for(ChoiceBox<Produto> select : listaItems.keySet()){
                TextField quantidade = listaItems.get(select);
                Produto p = select.getSelectionModel().getSelectedItem();
                if((p != null)) {
                    int qtd = Integer.parseInt(quantidade.getText());
                    if(p.getQuantidade() >= qtd){
                        listaNF.put(p, qtd);
                    }else{
                        erro = true;
                    }
                }
            }
        } catch(Exception e) {
            alertaNF.setText("Confira se todos os campos foram inseridos corretamente!");
        }
        if (erro) {
            alertaNF.setText("Você não pode inserir mais do que há no estoque :/");
            return;
        }
        if(addNotaFiscal(new NotaFiscal(listaNF))) {
            goVoltarEmpresa();
            atualizarListaNFs();
        }
    }
    void setProdutosSelect(ChoiceBox<Produto> select){
        for(Produto p : minhaEmpresa.getProdutos())
            select.getItems().add(p);
    }
    void limpaInputs(){
        listaItems.clear();
        produtoInicial.getItems().clear();
        qtdInicial.setText("");
        if(panelInputs.getChildren().size() > 2)
            panelInputs.getChildren().remove(2, panelInputs.getChildren().size());
    }

    @Override
    public boolean addNotaFiscal(NotaFiscal nf) {
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
