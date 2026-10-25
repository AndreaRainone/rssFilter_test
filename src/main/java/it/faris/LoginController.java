package it.faris;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label userLabel;
    @FXML private Label passLabel;

    @FXML
    public void initialize() {
        setupFloatingLabel(usernameField, userLabel);
        setupFloatingLabel(passwordField, passLabel);
    }

    private void setupFloatingLabel(TextField field, Label label) {
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused || !field.getText().isEmpty()) {
                // Passa "true" per il grassetto quando va in alto
                animateLabel(label, -22, "#991845", 12, true);
                field.setStyle("-fx-border-color: #991845; -fx-effect: dropshadow(gaussian, rgba(153,24,69,0.6), 18, 0.2, 0, 0);");
            } else {
                // Passa "false" per tornare normale quando scende
                animateLabel(label, 0, "#999999", 14, false);
                field.setStyle("-fx-border-color: #E2DCC8; -fx-effect: none;");
            }
        });
    }

    private void animateLabel(Label label, double y, String color, int fontSize, boolean isBold) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(200), label);
        tt.setToY(y);
        tt.play();

        String fontWeight = isBold ? "bold" : "normal";

        // Se l'etichetta sale (y < 0), applichiamo un colore solido simile al Vanilla Cloud per coprire il bordo.
        // Se scende al centro (y == 0), torna trasparente.
        // Nota: se noti uno stacco netto col gradiente della card, puoi aggiustare l'HEX (es. #FEFAEB).
        String bgColor = (y < 0) ? "#FDF9EA" : "transparent";

        label.setStyle(
                "-fx-text-fill: " + color + ";" +
                        "-fx-font-size: " + fontSize + "px;" +
                        "-fx-font-weight: " + fontWeight + ";" +
                        "-fx-padding: 0 5 0 5;" +
                        "-fx-background-color: " + bgColor + ";"
        );
    }

    @FXML private void closeApp() { System.exit(0); }
    @FXML private void handleLogin() { System.out.println("Login cliccato!"); }
    @FXML private void openRegistration() { System.out.println("Apri registrazione"); }
}