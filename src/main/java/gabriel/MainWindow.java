package gabriel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author JavaFX tutorial
 * Credits to JavaFX tutorial for the FXML code,MainWindow and DialogBox layout
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gabriel gabriel;

    private Image pikachuImage = new Image(this.getClass().getResourceAsStream("/images/daPikachu.png"));
    private Image psyduckImage = new Image(this.getClass().getResourceAsStream("/images/daPsyduck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setGabriel(Gabriel g) {
        gabriel = g;
        String welcomeText = gabriel.getWelcomeMessage();
        dialogContainer.getChildren().addAll(DialogBox.getGabrielDialog(welcomeText, psyduckImage));
        String loadingText = gabriel.getLoadingMessage();
        if (!loadingText.isEmpty()) {
            dialogContainer.getChildren().addAll(DialogBox.getGabrielDialog(loadingText, psyduckImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gabriel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, pikachuImage),
                DialogBox.getGabrielDialog(response, psyduckImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
