package it.faris;

import it.faris.Notizia; // Sostituisci con il package del tuo modello Notizia
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BachecaLayoutController {

    @FXML private HBox mainCard;
    @FXML private StackPane centerContentArea;
    @FXML private ToggleButton btnNavBacheca, btnNavSorgenti, btnNavProfilo;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        // Gestione trascinamento della finestra trasparente
        if (mainCard != null) {
            mainCard.setOnMousePressed(e -> {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            mainCard.setOnMouseDragged(e -> {
                Node src = (Node) e.getSource();
                src.getScene().getWindow().setX(e.getScreenX() - xOffset);
                src.getScene().getWindow().setY(e.getScreenY() - yOffset);
            });
        }

        // Carica la vista del feed iniziale al centro
        loadBachecaView();
    }

    @FXML
    public void loadBachecaView() {
        setNavActive(btnNavBacheca);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bacheca_feed_view.fxml"));
            Parent view = loader.load();

            // Inietta il riferimento del layout controller nel feed controller
            BachecaFeedController feedController = loader.getController();
            if (feedController != null) {
                feedController.setLayoutController(this);
            }

            centerContentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di bacheca_feed_view.fxml");
            e.printStackTrace();
        }
    }

    /**
     * Carica il Dettaglio Notizia all'interno del centro del layout
     */
    public void openArticleDetail(Notizia notizia) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ArticleDetailView.fxml"));
            Parent view = loader.load();

            // Inietta il layout controller e l'oggetto Notizia nel controller di dettaglio
            ArticleDetailController detailController = loader.getController();
            if (detailController != null) {
                detailController.setLayoutController(this);
                detailController.setNotizia(notizia);
            }

            centerContentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di ArticleDetailView.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void loadSorgentiView() {
        setNavActive(btnNavSorgenti);
    }

    @FXML
    private void loadProfiloView() {
        setNavActive(btnNavProfilo);
    }

    private void setNavActive(ToggleButton activeBtn) {
        btnNavBacheca.setSelected(false);
        btnNavSorgenti.setSelected(false);
        btnNavProfilo.setSelected(false);
        activeBtn.setSelected(true);
    }

    // --- CONTROLLI FINESTRA (RIDIMENSIONAMENTO) ---
    @FXML
    private void minimizeApp() {
        if (mainCard != null && mainCard.getScene() != null) {
            Stage stage = (Stage) mainCard.getScene().getWindow();
            stage.setIconified(true);
        }
    }

    @FXML
    private void toggleMaximizeApp() {
        if (mainCard != null && mainCard.getScene() != null) {
            Stage stage = (Stage) mainCard.getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        }
    }

    @FXML
    private void closeApp() {
        if (mainCard != null && mainCard.getScene() != null) {
            ((Stage) mainCard.getScene().getWindow()).close();
        }
    }

    /**
     * Apre l'Assistente Conversazionale. Se passi 'null' si apre in modalità generale.
     */
    public void openAIAssistant(Notizia contextNews) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConversationalChatView.fxml"));
            Parent view = loader.load();

            ConversationalChatController chatController = loader.getController();
            chatController.setLayoutController(this);

            // Inizializza l'interfaccia a seconda del contesto
            if (contextNews != null) {
                chatController.setNewsContext(contextNews);
            } else {
                chatController.setGeneralContext();
            }

            centerContentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di ConversationalChatView.fxml");
            e.printStackTrace();
        }
    }

    // Metodo collegato al bottone "Apri Assistente" della barra laterale o bacheca
    @FXML
    private void openAIAssistantGeneral() {
        openAIAssistant(null); // Passa null per la modalità generale
    }

    @FXML private void forceSync() { System.out.println("Forzatura sincronizzazione feed RSS (RF_GSF_04)..."); }
    @FXML private void handleLogout() { closeApp(); }

    /**
     * Apre il form nota. Se passi una nota esistente (id e testo), si apre in modifica, altrimenti in aggiunta.
     */
    public void openAddNoteForm(Notizia notizia, String notaId, String testoEsistente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNoteView.fxml"));
            Parent view = loader.load();

            AddNoteController noteController = loader.getController();
            if (noteController != null) {
                noteController.setLayoutController(this);
                noteController.setNotizia(notizia);

                if (notaId != null) {
                    noteController.setNotaDaModificare(notaId, testoEsistente);
                }
            }

            centerContentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di AddNoteView.fxml");
            e.printStackTrace();
        }
    }

    /**
     * Overload per aprire il form nota in modalità AGGIUNTA (senza nota esistente)
     */
    public void openAddNoteForm(Notizia notizia) {
        openAddNoteForm(notizia, null, null);
    }
}