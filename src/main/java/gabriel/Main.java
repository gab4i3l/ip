package gabriel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author JavaFX tutorial
 * Credits to JavaFX tutorial for the FXML code,MainWindow and DialogBox layout.
 * A GUI for Gabriel using FXML.
 */
public class Main extends Application {

    private Gabriel gabriel = new Gabriel("./data/Gabriel.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGabriel(gabriel); // inject the Gabriel instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
