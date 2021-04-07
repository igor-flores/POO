package Controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.*;

import static javafx.fxml.FXMLLoader.*;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene, fotoViewScene, fotoCreateScene, filmeViewScene;

    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    /** Setando telas */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("Trabalho Prático");
        primaryStage.setMaximized(true);

        /* Bug ao mudar scene
         * https://stackoverflow.com/questions/41606606/start-the-application-window-maximized-in-javafx-fxml-not-working-properly
         */
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        homeScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/Home.fxml"))));
        fotoViewScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FotoVisualizar.fxml"))));
        fotoCreateScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FotoCreate.fxml"))));
        filmeViewScene = new Scene(load(Objects.requireNonNull(getClass().getResource("../View/FilmeVisualizar.fxml"))));

        primaryStage.setScene(homeScene);
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
            case "fotoVisualizar": stage.setScene(fotoViewScene); break;
            case "fotoCreate": stage.setScene(fotoCreateScene); break;
            case "filmeVisualizar": stage.setScene(filmeViewScene); break;
            default: stage.setScene(homeScene);
        }
        notifyAllListeners(screen, userData);
    }
    public static void changeScreen(String screen){ changeScreen(screen, null); }


    /**
     * Esta interface obriga todos os controllers a receber a tela atual bem como um parametro
     */
    public interface OnChangeScreen{ void screenChanged(String newScreen, ArrayList<String> userData); }

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
    private static void notifyAllListeners(String newScreen, ArrayList<String> userData){
        for (OnChangeScreen l : listeners)
            l.screenChanged(newScreen, userData);
    }

}
