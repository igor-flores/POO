package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProdutoController extends EmpresaController {
    private Produto produtoEdit;

    @FXML private Label msgTitulo, msgDescricao, msgPreco, msgQtdEstoque;
    @FXML private TextField inputNome, inputDescricao,  inputPreco, inputQtdEstoque;
    @FXML private ToggleGroup tipoProduto;

    @FXML protected void initialize(){
        Main.setListener(new Main.OnChangeScreen() {
            @Override
            public void screenChanged(String newScreen, Object userData) {
                if(newScreen.equals("ProdutoEdit")){
                    if (inputNome != null) {
                        produtoEdit = (Produto) userData;
                        setInputsEdit();
                    }

                }else if(newScreen.equals("ProdutoAdd")){
                    if (inputNome != null) limpaInputs();
                }
                if (minhaEmpresa != null) nomeEmpresa.setText(minhaEmpresa.getNome() + "!");
            }
        });
    }


    @FXML void editarProduto() {
        produtoEdit.setNome(inputNome.getText());
        produtoEdit.setDescricao(inputDescricao.getText());
        produtoEdit.setPreco(Float.parseFloat(inputPreco.getText()));
        produtoEdit.setQuantidade(Float.parseFloat(inputQtdEstoque.getText()));

        goVoltarEmpresa();
    }

    @FXML void adicionarProduto() {
        if(!confereInputs()) return;
        RadioButton radio = (RadioButton) tipoProduto.getSelectedToggle();
        if(radio.getText().equals("Produto por Kg")){
            addProduto(new Produto(
                    inputNome.getText(),
                    inputDescricao.getText(),
                    Float.parseFloat(inputPreco.getText()),
                    Float.parseFloat(inputQtdEstoque.getText())
            ));
        }else{
            addProduto(new PerUnid(
                    inputNome.getText(),
                    inputDescricao.getText(),
                    Float.parseFloat(inputPreco.getText()),
                    Float.parseFloat(inputQtdEstoque.getText())
            ));
        }
        goVoltarEmpresa();
    }

    boolean confereInputs(){
        int erro = 0;
        if(inputNome.getText().isEmpty()) {
            msgTitulo.setText("Este campo é obrigatório. ");
            erro = 1;
        } if(inputDescricao.getText().isEmpty()) {
            msgDescricao.setText("Este campo é obrigatório. ");
            erro = 1;
        } if(inputPreco.getText().isEmpty()) {
            msgPreco.setText("Este campo é obrigatório. ");
            erro = 1;
        } if(inputQtdEstoque.getText().isEmpty()) {
            msgQtdEstoque.setText("Este campo é obrigatório. ");
            erro = 1;
        }
        return (erro == 0);
    }
    void setInputsEdit(){
        inputNome.setText(produtoEdit.getNome());
        inputDescricao.setText(produtoEdit.getDescricao());
        inputPreco.setText(Float.toString(produtoEdit.getPreco()));
        inputQtdEstoque.setText(Float.toString(produtoEdit.getQuantidade()));
    }
    void limpaInputs(){
        inputNome.setText("");
        inputDescricao.setText("");
        inputPreco.setText("");
        inputQtdEstoque.setText("");
    }

    @Override
    public boolean addProduto(Produto p) {
        minhaEmpresa.getProdutos().add(p);
        return true;
    }

}
