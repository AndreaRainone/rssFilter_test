package it.faris;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class Step2Controller implements WizardStep {
    @FXML private VBox rootContainer;
    @FXML private TextField nomeField;
    @FXML private TextField cognomeField;
    @FXML private DatePicker dataNascitaPicker;
    @FXML private ComboBox<String> nazioneComboBox;

    private WizardBaseController base;
    private RegistrationViewModel vm;

    @Override
    public void setup(WizardBaseController base, RegistrationViewModel vm) {
        this.base = base;
        this.vm = vm;

        nomeField.textProperty().bindBidirectional(vm.nome);
        cognomeField.textProperty().bindBidirectional(vm.cognome);

        // --- POPOLAMENTO NAZIONI (Ricerca nativa da tastiera attiva) ---
        var paesi = Arrays.stream(Locale.getISOCountries())
                .map(code -> new Locale("", code).getDisplayCountry(Locale.ITALIAN))
                .filter(name -> !name.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        nazioneComboBox.setItems(FXCollections.observableArrayList(paesi));

        if (vm.nazione.get() != null && !vm.nazione.get().isEmpty()) {
            nazioneComboBox.setValue(vm.nazione.get());
        }
        nazioneComboBox.valueProperty().addListener((obs, oldV, newV) -> vm.nazione.set(newV));


        // 2. Insegniamo a JavaFX a leggere e scrivere la data in formato italiano
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataNascitaPicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, formatter);
                    } catch (Exception e) {
                        return null; // Se l'utente scrive male, ignora
                    }
                }
                return null;
            }
        });

        // 3. Binding bidirezionale standard
        if (vm.dataNascita.get() != null) {
            dataNascitaPicker.setValue(vm.dataNascita.get());
        }
        dataNascitaPicker.valueProperty().addListener((obs, oldV, newV) -> vm.dataNascita.set(newV));

        // Toglie il focus automatico dal primo campo
        Platform.runLater(() -> rootContainer.requestFocus());
    }

    @FXML private void goBack() { base.loadStep("/step1_account.fxml", 1); }
    @FXML private void goNext() { base.loadStep("/step3_token.fxml", 3); }
}