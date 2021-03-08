package Controller;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private static final ArrayList<Empresa> empresas = new ArrayList<>();
    private static Stage stage;
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    private static Scene homeScene, empresaScene, produtoAddScene, produtoEditScene, nfAddScene, nfEditScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Trabalho Prático");

        homeScene = new Scene(FXMLLoader.load(getClass().getResource("../View/Home.fxml")), 1000, 600);
        empresaScene = new Scene(FXMLLoader.load(getClass().getResource("../View/Empresa.fxml")), 1000, 600);
        produtoAddScene = new Scene(FXMLLoader.load(getClass().getResource("../View/ProdutoAdd.fxml")), 1000, 600);
        produtoEditScene = new Scene(FXMLLoader.load(getClass().getResource("../View/ProdutoEdit.fxml")), 1000, 600);
        nfAddScene = new Scene(FXMLLoader.load(getClass().getResource("../View/NotaFiscalAdd.fxml")), 1000, 600);
//        Parent fxmlNFEdit = FXMLLoader.load(getClass().getResource("../View/ProdutoEdit.fxml")); nfEditScene = new Scene(fxmlProdutoEdit, 1000, 600);

        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        if(!Arquivos.criaRaiz())
            return;
        for (String empresa : Arquivos.leArquivo(Arquivos._DIR_EMPRESA_)){
            String[] dadosEmpresa = empresa.split(";");
            ArrayList<Produto> produtos = new ArrayList<>();
            for(String produto : Arquivos.leArquivo(Arquivos._DIR_PRODUTO_)){
                String[] dadosProduto = produto.split(";");
                if(Integer.parseInt(dadosProduto[5]) == (1000 + Empresa.getQuantidadeEmpresas())){
                    if(dadosProduto[0].equals("0")){
                        produtos.add(new PerUnid(
                                dadosProduto[1],
                                dadosProduto[2],
                                Float.parseFloat(dadosProduto[3]),
                                Integer.parseInt(dadosProduto[4])
                        ));
                    }else{
                        produtos.add(new Produto(
                                dadosProduto[1],
                                dadosProduto[2],
                                Float.parseFloat(dadosProduto[3]),
                                Float.parseFloat(dadosProduto[4])
                        ));
                    }
                }
            }
            empresas.add(new Empresa(
                    dadosEmpresa[0],
                    dadosEmpresa[1],
                    dadosEmpresa[2],
                    produtos
            ));
        }

        launch(args);
    }

    public static void changeScreen(String screen){ changeScreen(screen, null); }
    public static void changeScreen(String screen, Object userData){
        switch (screen){
            case "Empresa": stage.setScene(empresaScene); break;
            case "ProdutoAdd": stage.setScene(produtoAddScene); break;
            case "ProdutoEdit": stage.setScene(produtoEditScene); break;
            case "NotaFiscalAdd": stage.setScene(nfAddScene); break;
            default: stage.setScene(homeScene);
        }
        notifyAllListeners(screen, userData);
    }


    public interface OnChangeScreen{ void screenChanged(String newScreen, Object userData); }
    public static void setListener(OnChangeScreen newListener){ listeners.add(newListener); }
    private static void notifyAllListeners(String newScreen, Object userData){
        for (OnChangeScreen l : listeners)
            l.screenChanged(newScreen, userData);
    }

    public static ArrayList<Empresa> getEmpresas() { return empresas; }
    public static void setEmpresas(Empresa empresa) { Main.empresas.add(empresa); }
}
