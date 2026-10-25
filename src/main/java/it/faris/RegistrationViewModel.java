package it.faris;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistrationViewModel {
    public final StringProperty username = new SimpleStringProperty("");
    public final StringProperty password = new SimpleStringProperty("");
    public final StringProperty nome = new SimpleStringProperty("");
    public final StringProperty cognome = new SimpleStringProperty("");
    public final StringProperty token = new SimpleStringProperty("");
}