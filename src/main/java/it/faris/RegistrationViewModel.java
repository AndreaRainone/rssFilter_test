package it.faris;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class RegistrationViewModel {
    public final StringProperty username = new SimpleStringProperty("");
    public final StringProperty password = new SimpleStringProperty("");
    public final StringProperty nome = new SimpleStringProperty("");
    public final StringProperty cognome = new SimpleStringProperty("");
    public final ObjectProperty<LocalDate> dataNascita = new SimpleObjectProperty<>();
    public final StringProperty nazione = new SimpleStringProperty("");
    public final StringProperty token = new SimpleStringProperty("");
}