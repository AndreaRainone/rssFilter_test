package it.faris;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BachecaFeedController {

    @FXML private TextField searchField;
    @FXML private ImageView imgNews1, imgNews2;

    @FXML
    public void initialize() {
        // Inizializzazione eventuale dei feed o caricamento asincrono immagini
    }

    @FXML
    private void handleReadArticle() {
        System.out.println("Consultazione articolo (RF_GBN_02)...");
    }

    @FXML
    private void handleAddNote() {
        System.out.println("Gestione/Inserimento Nota Esperto (RF_GNE_01)...");
    }
}