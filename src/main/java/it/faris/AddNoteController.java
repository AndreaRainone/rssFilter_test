package it.faris;

import it.faris.Notizia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AddNoteController {

    @FXML private Label lblHeaderTitle; // Puoi aggiungerlo opzionalmente nell'intestazione o nel titolo della finestra
    @FXML private Label lblNewsTitle;
    @FXML private TextArea txtNoteContent;
    @FXML private Button btnSave;
    @FXML private Button btnDelete; // Pulsante per la rimozione della nota

    private BachecaLayoutController layoutController;
    private Notizia notiziaCorrente;
    private String notaEsistenteId; // Se è null siamo in aggiunta, se è valorizzato siamo in modifica

    public void setLayoutController(BachecaLayoutController layoutController) {
        this.layoutController = layoutController;
    }

    public void setNotizia(Notizia notizia) {
        this.notiziaCorrente = notizia;
        if (notizia != null && lblNewsTitle != null) {
            lblNewsTitle.setText(notizia.getTitolo());
        }
    }

    /**
     * Configura il form in modalità MODIFICA caricando il testo esistente
     */
    public void setNotaDaModificare(String notaId, String testoIniziale) {
        this.notaEsistenteId = notaId;

        // Cambia il titolo della finestra
        if (lblHeaderTitle != null) {
            lblHeaderTitle.setText("Modifica Nota Esperto");
        }

        if (txtNoteContent != null) {
            txtNoteContent.setText(testoIniziale);
        }
        if (btnSave != null) {
            btnSave.setText("Salva Modifiche ✓");
        }
        if (btnDelete != null) {
            btnDelete.setVisible(true);
            btnDelete.setManaged(true);
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        String testoNota = txtNoteContent.getText().trim();

        if (testoNota.isEmpty()) {
            System.out.println("Errore: Il testo della nota non può essere vuoto.");
            return;
        }

        if (notaEsistenteId == null) {
            // RF_GNE_01: AGGIUNTA NOTA
            System.out.println("Creazione nuova nota per l'articolo: " + notiziaCorrente.getTitolo());
            // Logica di salvataggio nuova nota...
        } else {
            // RF_GNE_03: MODIFICA NOTA
            System.out.println("Aggiornamento nota ID: " + notaEsistenteId);
            // Logica di aggiornamento nota esistente...
        }

        tornaAlDettaglio();
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        // RF_GNE_02: RIMOZIONE NOTA
        if (notaEsistenteId != null) {
            System.out.println("Rimozione nota ID: " + notaEsistenteId);
            // Logica di cancellazione da database/collezione...
        }
        tornaAlDettaglio();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        tornaAlDettaglio();
    }

    private void tornaAlDettaglio() {
        if (layoutController != null && notiziaCorrente != null) {
            layoutController.openArticleDetail(notiziaCorrente);
        }
    }
}