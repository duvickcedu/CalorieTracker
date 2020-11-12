package christian.duvick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This project is a calorie tracker
 * It extends application so therefore it has a GUI
 * @author Christian Duvick
 */
public class Driver extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        int HEIGHT = 400;
        int WIDTH = 600;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Calorie Tracker");
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
