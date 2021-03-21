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
        Main.setListener((newScreen, userData) -> {
            if(newScreen.equals("ProdutoEdit")){
                if (inputNome != null) {
                    produtoEdit = (Produto) userData;
                    setInputsEdit();
                }

            }else if(newScreen.equals("ProdutoAdd")){
                if (inputNome != null) limpaInputs();
            }
            if (minhaEmpresa != null) nomeEmpresa.setText(minhaEmpresa.getNome() + "!");
        });
    }


    @FXML void editarProduto() {
        produtoEdit.setNome(inputNome.getText());
        produtoEdit.setDescricao(inputDescricao.getText());
        produtoEdit.setPreco(Double.parseDouble(inputPreco.getText()));
        produtoEdit.setQuantidade(Double.parseDouble(inputQtdEstoque.getText()));

        goVoltarEmpresa();
    }

    @FXML void adicionarProduto() {
        if(!confereInputs()) return;
        RadioButton radio = (RadioButton) tipoProduto.getSelectedToggle();
        if(radio.getText().equals("Produto por Kg")){
            addProduto(new Produto(
                inputNome.getText(),
                inputDescricao.getText(),
                Double.parseDouble(inputPreco.getText()),
                Double.parseDouble(inputQtdEstoque.getText())
            ));
        }else{
            addProduto(new PerUnid(
                inputNome.getText(),
                inputDescricao.getText(),
                Double.parseDouble(inputPreco.getText()),
                Double.parseDouble(inputQtdEstoque.getText())
            ));
        }
        goVoltarEmpresa();
    }

    /**
     * Procura por campos obrigatórios não preenchidos
     * @return true para erros
     */
    boolean confereInputs(){
        boolean erro = false;
        if(inputNome.getText().isEmpty()) {
            msgTitulo.setText("Este campo é obrigatório. ");
            erro = true;
        } if(inputDescricao.getText().isEmpty()) {
            msgDescricao.setText("Este campo é obrigatório. ");
            erro = true;
        } if(inputPreco.getText().isEmpty()) {
            msgPreco.setText("Este campo é obrigatório. ");
            erro = true;
        } if(inputQtdEstoque.getText().isEmpty()) {
            msgQtdEstoque.setText("Este campo é obrigatório. ");
            erro = true;
        }
        return erro;
    }

    /** Pegará o produtoEditado no momento
     * Setará os inputs com o produto
     */
    void setInputsEdit(){
        inputNome.setText(produtoEdit.getNome());
        inputDescricao.setText(produtoEdit.getDescricao());
        inputPreco.setText(Double.toString(produtoEdit.getPreco()));
        inputQtdEstoque.setText(Double.toString(produtoEdit.getQuantidade()));
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
