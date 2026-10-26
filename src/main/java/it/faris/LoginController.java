package it.faris;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void initialize() {
        // Nessuna animazione manuale necessaria.
        // L'interfaccia usa il promptText nativo e il CSS per il focus.
    }

    // Il parametro DEVE essere MouseEvent perché usiamo onMouseClicked sulla Label FXML
    @FXML
    private void openRegistration(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/wizard_base.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);

            Stage registrationStage = new Stage();
            registrationStage.initStyle(StageStyle.TRANSPARENT);
            registrationStage.setScene(scene);
            registrationStage.centerOnScreen();
            registrationStage.show();

            // Chiude in modo sicuro l'attuale finestra di login
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeApp() {
        System.exit(0); // Chiude l'intera applicazione
    }
}