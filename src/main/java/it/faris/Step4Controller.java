package it.faris;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Step4Controller implements WizardStep {
    @FXML private Label lblUser, lblNome, lblToken;
    private WizardBaseController base;
    private RegistrationViewModel vm;

    @Override
    public void setup(WizardBaseController base, RegistrationViewModel vm) {
        this.base = base; this.vm = vm;

        lblUser.setText("Username: " + vm.username.get());
        lblNome.setText("Utente: " + vm.nome.get() + " " + vm.cognome.get());

        if(vm.token.get() != null && !vm.token.get().trim().isEmpty()) {
            lblToken.setText("Ruolo: Esperto (Token inserito)");
        } else {
            lblToken.setText("Ruolo: Lettore Base");
        }
    }

    @FXML private void goBack() { base.loadStep("/step3_token.fxml", 3); }
    @FXML private void submit() {
        System.out.println("Registrazione completata per: " + vm.username.get());
        // Qui chiami l'UseCase per il database!
    }
}