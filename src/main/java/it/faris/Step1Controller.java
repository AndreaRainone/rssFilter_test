package it.faris;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Step1Controller implements WizardStep {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    private WizardBaseController baseController;
    private RegistrationViewModel viewModel;

    @Override
    public void setup(WizardBaseController baseController, RegistrationViewModel viewModel) {
        this.baseController = baseController;
        this.viewModel = viewModel;
        usernameField.textProperty().bindBidirectional(viewModel.username);
        passwordField.textProperty().bindBidirectional(viewModel.password);
    }

    @FXML
    private void goNext() {
        System.out.println("Username salvato nel ViewModel: " + viewModel.username.get());
        baseController.loadStep("/step2_anagrafica.fxml", 2); // Scommenta quando crei lo step 2
    }
}