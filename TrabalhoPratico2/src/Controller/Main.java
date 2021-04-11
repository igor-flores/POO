package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

import static javafx.fxml.FXMLLoader.*;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene;
    private static Scene fotoCreateScene, fotoReadScene, fotoUpdateScene;
    private static Scene filmeCreateScene, filmeReadScene;
    private static Scene musicaCreateScene, musicaReadScene;

    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    /** Setando telas */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Trabalho Prático 2");
        primaryStage.setMaximized(true);

        homeScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/Home.fxml"))));

        fotoCreateScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FotoCreate.fxml"))));
        fotoReadScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FotoRead.fxml"))));
        fotoUpdateScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FotoUpdate.fxml"))));

        filmeReadScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FilmeRead.fxml"))));
        filmeCreateScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FilmeCreate.fxml"))));

        musicaCreateScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/MusicaCreate.fxml"))));
        musicaReadScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/MusicaRead.fxml"))));

        changeScreen("Home");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Controla as trocas de tela
     * @param screen = passa o "nome" da tela desejada
     * @param userData = parametro Opcional
     */
    public static void changeScreen(String screen, ArrayList<String> userData){
        switch (screen){
            case "fotoCreate": stage.setScene(fotoCreateScene); break;
            case "fotoRead": stage.setScene(fotoReadScene); break;
            case "fotoUpdate": stage.setScene(fotoUpdateScene); break;

            case "filmeCreate": stage.setScene(filmeCreateScene); break;
            case "filmeRead": stage.setScene(filmeReadScene); break;

            case "musicaCreate": stage.setScene(musicaCreateScene); break;
            case "musicaRead": stage.setScene(musicaReadScene); break;
            default: stage.setScene(homeScene);
        }
        notifyAllListeners(screen, userData);
    }
    public static void changeScreen(String screen){ changeScreen(screen, null); }


    /**
     * Esta interface obriga todos os controllers a receber a tela atual bem como um parametro
     */
    public interface OnChangeScreen{
        void screenChanged(String newScreen, ArrayList<String> userData);
    }

    /**
     * @param newListener é a interface, assim a lista listeners terá todas os locais que a interface foi implementada
     */
    public static void setListener(OnChangeScreen newListener){ listeners.add(newListener); }

    /**
     * Notificará todas os controllers
     * @param newScreen = tela atual
     * @param userData = algum objeto, podendo ser nulo ou não
     */
    private static void notifyAllListeners(String newScreen, ArrayList<String> userData){
        for (OnChangeScreen l : listeners)
            l.screenChanged(newScreen, userData);
    }

}
