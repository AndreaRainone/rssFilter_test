package it.faris;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class Step4Controller implements WizardStep {
    @FXML private Label lblUser, lblNome, lblDataNascita, lblNazione, lblPassword, lblToken, eyeIcon;
    private WizardBaseController base;
    private RegistrationViewModel vm;
    private boolean isPasswordVisible = false;

    @Override
    public void setup(WizardBaseController base, RegistrationViewModel vm) {
        this.base = base;
        this.vm = vm;

        lblUser.setText(vm.username.get());
        lblNome.setText(vm.nome.get() + " " + vm.cognome.get());
        lblNazione.setText(vm.nazione.get() != null && !vm.nazione.get().isEmpty() ? vm.nazione.get() : "Non specificata");

        // ******** CONTROLLO E FORMATTAZIONE DATA *****************TEST
        if (vm.dataNascita.getValue() == null) {
            lblDataNascita.setText("Non Specificato");
        } else {
            // Formatta la data in formato italiano (dd/MM/yyyy) coerente con lo Step 2
            java.time.LocalDate data = vm.dataNascita.getValue();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblDataNascita.setText(formatter.format(data));
        }
        //**********************************************************

        String tokenVal = vm.token.get();
        if (tokenVal != null && !tokenVal.trim().isEmpty()) {
            lblToken.setText("Esperto (" + tokenVal + ")");
        } else {
            lblToken.setText("Lettore");
        }

        lblPassword.setText("********");
    }

    @FXML
    private void togglePassword() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            lblPassword.setText(vm.password.get());
            eyeIcon.setText("🙈");
        } else {
            lblPassword.setText("********");
            eyeIcon.setText("👁");
        }
    }

    @FXML private void goBack() { base.loadStep("/step3_token.fxml", 3); }
    @FXML private void closeApp() { System.exit(0); }

    @FXML
    private void submit() {
        System.out.println("Registrazione completata con successo!");

        try {
            // 1. Carica il form degli interessi che abbiamo appena creato
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/interessi_form.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

            javafx.stage.Stage interessiStage = new javafx.stage.Stage();
            interessiStage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
            interessiStage.setScene(scene);
            interessiStage.centerOnScreen();
            interessiStage.show();

            // 2. Chiude la finestra corrente del Wizard di registrazione
            javafx.stage.Stage currentStage = (javafx.stage.Stage) lblUser.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}