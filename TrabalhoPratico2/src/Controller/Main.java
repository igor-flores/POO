package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

import Model.*;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene;
    private static String nome = "igor";

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
//        Connect.conector();

//        launch(args);
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
