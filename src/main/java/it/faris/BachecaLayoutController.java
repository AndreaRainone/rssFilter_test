package it.faris;

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
    private void loadBachecaView() {
        setNavActive(btnNavBacheca);
        injectView("/bacheca_feed_view.fxml");
    }

    @FXML
    private void loadSorgentiView() {
        setNavActive(btnNavSorgenti);
    }

    @FXML
    private void loadProfiloView() {
        setNavActive(btnNavProfilo);
    }

    private void injectView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            centerContentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("Errore nell'iniezione della vista: " + fxmlPath);
            e.printStackTrace();
        }
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

    @FXML private void openAIAssistant() { System.out.println("Apertura finestra Assistente AI (RF_GAC_01)..."); }
    @FXML private void forceSync() { System.out.println("Forzatura sincronizzazione feed RSS (RF_GSF_04)..."); }
    @FXML private void handleLogout() { closeApp(); }
}