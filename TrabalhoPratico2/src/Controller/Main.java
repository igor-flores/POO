package Controller;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene;

    public static BancoMidia banco;
    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    /** Setando telas */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Trabalho Prático");

        homeScene = new Scene(FXMLLoader.load(getClass().getResource("../View/Home.fxml")), 1000, 600);

        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    /** Setando empresas com produtos */
    public static void main(String[] args) {
        banco = new BancoMidia();
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Pessoa vo = new Pessoa("Vó");
        Pessoa eu = new Pessoa("Guinho");
        pessoas.add(vo);
        pessoas.add(eu);
        banco.getFotos().add(
            new Foto(
                "/pasta/imagem/",
                "Goi",
                "goi da vovó",
                new Date(),
                vo,
                "casa da vovó",
                pessoas
            )
        );
        banco.getFotos().add(
            new Foto(
                "/pasta/imagem/",
                "Abar",
                "goi da vovó",
                new Date("2021/07/15"),
                vo,
                "casa da vovó",
                pessoas
            )
        );
        banco.getFilmes().add(
            new Filme(
                "/pasta/filme/",
                "Procurando nemo",
                "blá blá blá",
                "infantil",
                "português",
                "120h",
                new Date("2018/01/01"),
                eu,
                pessoas
            )
        );
        banco.getFilmes().add(
            new Filme(
                "/pasta/filme/",
                "As aventuras de PI",
                "blá blá blá",
                "aventura",
                "português",
                "120h",
                new Date("2020/01/01"),
                eu,
                pessoas
            )
        );

        launch(args);
    }

    /**
     * Controla as trocas de tela
     * @param screen = passa o "nome" da tela desejada
     * @param userData = parametro Opcional
     */
    public static void changeScreen(String screen, Object userData){
        switch (screen){
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

}
