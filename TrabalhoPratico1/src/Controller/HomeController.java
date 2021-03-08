package Controller;

import Model.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Controller Tela Login e Cadastrar */
public class HomeController {

    /** Objetos manipulados na interface gráfica */
    @FXML private TextField inputCadastroEmail, inputEntrarEmail, inputCadastroNome;
    @FXML private PasswordField inputCadastroSenha,  inputEntrarSenha;
    @FXML private Label erroLogin, successCadastro, failedCadastro;

    /** Ao ser iniciada a tela */
    @FXML
    protected void initialize(){
        inputEntrarEmail.setText("igorflores.aluno@unipampa.edu.br");
        inputEntrarSenha.setText("123");
    }

    /** Confere se email e senha correspondem à alguma empresa inserida na lista de empresas cadastradas */
    @FXML
    void efetuarLogin() {
        for(Empresa e : Main.getEmpresas()){
            if(e.getEmail().equals(inputEntrarEmail.getText()) && e.getSenha().equals(inputEntrarSenha.getText())){
                Main.changeScreen("Empresa", e);
                return;
            }
        }
        erroLogin.setText("Email e/ou senha incorretos!");
    }

    /** Confere se já existe um email cadastrado, senão insere à lista de empresas*/
    @FXML
    void efetuarCadastro() {
        for(Empresa e : Main.getEmpresas()){
            if(e.getEmail().equals(inputEntrarEmail.getText())){
                failedCadastro.setText("Poxa, parece que já existe um email cadastrado :/");
                return;
            }
        }
        Empresa e = new Empresa(
            inputCadastroNome.getText(),
            inputCadastroEmail.getText(),
            inputCadastroSenha.getText()
        );
        Main.setEmpresas(e);
        successCadastro.setText("Conta criada com sucesso, agora é só entrar!");
    }
}
