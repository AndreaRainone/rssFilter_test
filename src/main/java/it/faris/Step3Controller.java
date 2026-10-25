package it.faris;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Step3Controller implements WizardStep {
    @FXML private TextField tokenField;
    private WizardBaseController base;
    private RegistrationViewModel vm;

    @Override
    public void setup(WizardBaseController base, RegistrationViewModel vm) {
        this.base = base; this.vm = vm;
        tokenField.textProperty().bindBidirectional(vm.token);
    }
    @FXML private void goBack() { base.loadStep("/step2_anagrafica.fxml", 2); }
    @FXML private void goNext() { base.loadStep("/step4_riepilogo.fxml", 4); }
}