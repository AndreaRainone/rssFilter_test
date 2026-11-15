package it.faris;

import it.faris.Notizia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConversationalChatController {

    @FXML private Label lblHeaderTitle;
    @FXML private HBox contextBadgeContainer;
    @FXML private Label lblContextNewsTitle;

    @FXML private VBox chatContainer;
    @FXML private ScrollPane scrollPane;
    @FXML private TextField txtPrompt;

    private BachecaLayoutController layoutController;
    private Notizia contextNotizia; // Null se siamo in modalità generale

    public void setLayoutController(BachecaLayoutController layoutController) {
        this.layoutController = layoutController;
    }

    /**
     * MODALITÀ 1: Avvio Generale dalla Bacheca
     */
    public void setGeneralContext() {
        this.contextNotizia = null;
        chatContainer.getChildren().clear();

        lblHeaderTitle.setText("Assistente Conversazionale FARIS");
        contextBadgeContainer.setVisible(false);
        contextBadgeContainer.setManaged(false);

        addAssistantMessage("Ciao! Sono il tuo assistente FARIS. Di cosa vorresti parlare o quale argomento vuoi cercare oggi?");
    }

    /**
     * MODALITÀ 2: Avvio da una Notizia Specifica
     */
    public void setNewsContext(Notizia notizia) {
        this.contextNotizia = notizia;
        chatContainer.getChildren().clear();

        lblHeaderTitle.setText("Approfondimento Notizia");
        lblContextNewsTitle.setText(notizia.getTitolo());

        contextBadgeContainer.setVisible(true);
        contextBadgeContainer.setManaged(true);

        addAssistantMessage("Ho caricato la notizia '" + notizia.getTitolo() + "'. Vuoi un riassunto dei punti chiave o un'analisi di dettaglio?");
    }

    @FXML
    private void handleClearContext(ActionEvent event) {
        setGeneralContext(); // Se l'utente chiude il badge, torna alla mod. generale
    }

    @FXML
    private void handleSendMessage(ActionEvent event) {
        String query = txtPrompt.getText().trim();
        if (query.isEmpty()) return;

        // 1. Mostra il messaggio dell'utente
        addUserMessage(query);
        txtPrompt.clear();

        // 2. Simula la risposta dell'LLM
        // (Qui in futuro inserirai la chiamata al backend LangChain/Ollama)
        addAssistantMessage("Sto analizzando la tua richiesta: \"" + query + "\"...");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        if (layoutController != null) {
            layoutController.loadBachecaView(); // Torna indietro alla bacheca
        }
    }

    // --- UTILITY PER CREARE I FUMETTI DELLA CHAT DINAMICAMENTE ---

    private void addAssistantMessage(String text) {
        VBox bubble = new VBox(6);
        bubble.getStyleClass().addAll("bubble", "bubble-assistant");
        bubble.setMaxWidth(520);

        Label lblAuthor = new Label("Assistente FARIS");
        lblAuthor.getStyleClass().add("bubble-author");

        Label lblText = new Label(text);
        lblText.getStyleClass().add("bubble-text-assistant");
        lblText.setWrapText(true);

        bubble.getChildren().addAll(lblAuthor, lblText);

        HBox row = new HBox(bubble);
        row.setAlignment(Pos.TOP_LEFT);
        row.getStyleClass().add("msg-row");

        chatContainer.getChildren().add(row);
        scrollToBottom();
    }

    private void addUserMessage(String text) {
        VBox bubble = new VBox(6);
        bubble.getStyleClass().addAll("bubble", "bubble-user");
        bubble.setMaxWidth(520);

        Label lblAuthor = new Label("Tu");
        lblAuthor.getStyleClass().add("bubble-author-user");

        Label lblText = new Label(text);
        lblText.getStyleClass().add("bubble-text-user");
        lblText.setWrapText(true);

        bubble.getChildren().addAll(lblAuthor, lblText);

        HBox row = new HBox(bubble);
        row.setAlignment(Pos.TOP_RIGHT);
        row.getStyleClass().add("msg-row");

        chatContainer.getChildren().add(row);
        scrollToBottom();
    }

    private void scrollToBottom() {
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }
}