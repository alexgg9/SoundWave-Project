package accesoadatos.soundwaveproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("login"), 742, 530);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static Scene createScene(String fxml, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {

        Parent p = loadFXML(fxml);
        Scene newScene;

        if (fxml.equals("login")) {
            newScene = createScene(fxml, 742, 530);
            primaryStage.setResizable(false);

        } else if (fxml.equals("list")) {
            newScene = createScene(fxml, 1068, 619);
            primaryStage.setResizable(false);
        } else if (fxml.equals("userProfile")) {
        newScene = createScene(fxml, 742, 530);
        primaryStage.setResizable(false);
        }else if (fxml.equals("discProfile")) {
            newScene = createScene(fxml, 742, 530);
            primaryStage.setResizable(false);
        }else if (fxml.equals("artistProfile")) {
            newScene = createScene(fxml, 742, 530);
            primaryStage.setResizable(false);
        }else if (fxml.equals("home")) {
            newScene = createScene(fxml, 742, 530);
            primaryStage.setResizable(false);
        } else {
            newScene = createScene(fxml, 640, 480);
            primaryStage.setResizable(true);
        }
        primaryStage.setScene(newScene);
        App.scene.setRoot(p);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
