package it.faris;

import it.faris.Notizia; // Sostituisci con il package del tuo modello Notizia
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BachecaFeedController {

    @FXML private TextField searchField;
    @FXML private ImageView imgNews1, imgNews2;

    private BachecaLayoutController layoutController;

    /**
     * Permette a BachecaLayoutController di iniettarsi qui
     */
    public void setLayoutController(BachecaLayoutController layoutController) {
        this.layoutController = layoutController;
    }

    @FXML
    public void initialize() {
        // Inizializzazione eventuale dei feed o caricamento asincrono immagini
    }

    @FXML
    private void handleReadArticle() {
        System.out.println("Consultazione articolo (RF_GBN_02)...");

        if (layoutController != null) {
            // Esempio di dati della notizia selezionata dal feed
            Notizia notiziaSelezionata = new Notizia(
                    "Rilasciata la nuova architettura esagonale per framework Java",
                    "Il nuovo aggiornamento introduce miglioramenti significativi nella gestione dei domini isolati...",
                    "Technology",
                    "TechCrunch",
                    "2 ore fa",
                    null
            );

            // Richiama la transizione della vista centrale
            layoutController.openArticleDetail(notiziaSelezionata);
        }
    }

    @FXML
    private void handleAddNote() {
        System.out.println("Gestione/Inserimento Nota Esperto (RF_GNE_01)...");
    }

    @FXML
    private void handleModificaNota() {
        System.out.println("Modifica Nota Esperto (RF_GNE_03)...");

        if (layoutController != null) {
            // Simuliamo il recupero della notizia che ha già una nota (come nel tuo mockup)
            Notizia notiziaConNota = new Notizia(
                    "Le Nazioni Unite chiedono un'azione globale più forte sul cambiamento climatico",
                    "Nuovi accordi internazionali fissano obiettivi rigorosi per la riduzione delle emissioni entro il 2030.",
                    "Global News",
                    "Reuters",
                    "4 ore fa",
                    null
            );

            // Simuliamo i dati della nota già presente nel sistema
            String idNotaEsistente = "NOTA_999";
            String testoEsistente = "Analisi dell'esperto: le implicazioni sui mercati energetici richiederanno un monitoraggio nel prossimo trimestre.";

            // Richiama il layoutController passando i 3 parametri (Notizia, ID Nota, Testo)
            layoutController.openAddNoteForm(notiziaConNota, idNotaEsistente, testoEsistente);
        }
    }
}