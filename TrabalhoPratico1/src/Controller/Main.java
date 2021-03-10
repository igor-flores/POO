package Controller;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene, empresaScene, produtoAddScene, produtoEditScene, nfViewScene, nfAddScene, nfEditScene;

    private static final ArrayList<Empresa> empresas = new ArrayList<>();
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    /** Setando telas */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Trabalho Prático");

        homeScene = new Scene(FXMLLoader.load(getClass().getResource("../View/Home.fxml")), 1000, 600);
        empresaScene = new Scene(FXMLLoader.load(getClass().getResource("../View/Empresa.fxml")), 1000, 600);
        produtoAddScene = new Scene(FXMLLoader.load(getClass().getResource("../View/ProdutoAdd.fxml")), 1000, 600);
        produtoEditScene = new Scene(FXMLLoader.load(getClass().getResource("../View/ProdutoEdit.fxml")), 1000, 600);
        nfViewScene = new Scene(FXMLLoader.load(getClass().getResource("../View/NotaFiscalSelected.fxml")), 1000, 600);
        nfAddScene = new Scene(FXMLLoader.load(getClass().getResource("../View/NotaFiscalAdd.fxml")), 1000, 600);
        nfEditScene = new Scene(FXMLLoader.load(getClass().getResource("../View/NotaFiscalEdit.fxml")), 1000, 600);

        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    /** Setando empresas com produtos */
    public static void main(String[] args) {
        String[] produtos = {"Maçã", "Banana", "Laranja", "Limão", "Uva", "Alface", "Brócolis", "Couve-flor", "Beterraba", "Abóbora"};
        Random random = new Random();
        for(int i = 1; i < 4; i++){
            ArrayList<Produto> listaProdutos = new ArrayList<>();
            for (int j = 0; j < 5; j++) listaProdutos.add(new Produto(produtos[j], "Produto blá blá blá", random.nextInt(101), random.nextInt(5)));
            for (int j = 5; j < 10; j++) listaProdutos.add(new PerUnid(produtos[j], "Produto blá blá blá", random.nextInt(101), random.nextInt(5)));

            empresas.add(new Empresa(("Loja " + i), ("loja" + i + "@email.com"), "123", listaProdutos));
        }

        launch(args);
    }

    /**
     * Controla as trocas de tela
     * @param screen = passa o "nome" da tela desejada
     * @param userData = parametro Opcional
     */
    public static void changeScreen(String screen, Object userData){
        switch (screen){
            case "Empresa": stage.setScene(empresaScene); break;
            case "ProdutoAdd": stage.setScene(produtoAddScene); break;
            case "ProdutoEdit": stage.setScene(produtoEditScene); break;
            case "NotaFiscalSelected": stage.setScene(nfViewScene); break;
            case "NotaFiscalAdd": stage.setScene(nfAddScene); break;
            case "NotaFiscalEdit": stage.setScene(nfEditScene); break;
            default: stage.setScene(homeScene);
        }
        notifyAllListeners(screen, userData);
    }
    public static void changeScreen(String screen){ changeScreen(screen, null); }


    /**
     * Esta interface obriga todos os controllers a receber a tela atual bem como um parametro
     */
    public interface OnChangeScreen{ void screenChanged(String newScreen, Object userData); }

    /**
     *
     * @param newListener é a interface, assim a lista listeners terá todas os locais que a interface foi implementada
     */
    public static void setListener(OnChangeScreen newListener){ listeners.add(newListener); }

    /**
     * Notificará todas os controllers
     * @param newScreen = tela atual
     * @param userData = algum objeto, podendo ser nulo ou não
     */
    private static void notifyAllListeners(String newScreen, Object userData){
        for (OnChangeScreen l : listeners)
            l.screenChanged(newScreen, userData);
    }

    public static ArrayList<Empresa> getEmpresas() { return empresas; }
    public static void setEmpresas(Empresa empresa) { Main.empresas.add(empresa); }
}
