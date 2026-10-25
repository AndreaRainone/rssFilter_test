package it.faris;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Step2Controller implements WizardStep {
    @FXML private TextField nomeField, cognomeField;
    private WizardBaseController base;
    private RegistrationViewModel vm;

    @Override
    public void setup(WizardBaseController base, RegistrationViewModel vm) {
        this.base = base; this.vm = vm;
        nomeField.textProperty().bindBidirectional(vm.nome);
        cognomeField.textProperty().bindBidirectional(vm.cognome);
    }
    @FXML private void goBack() { base.loadStep("/step1_account.fxml", 1); }
    @FXML private void goNext() { base.loadStep("/step3_token.fxml", 3); }
}