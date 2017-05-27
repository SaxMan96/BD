package Program;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Mateusz on 2017-05-10.
 */
public class Program extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(File.separator+"DatabaseView.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();

        Controller Controller = new Controller();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
