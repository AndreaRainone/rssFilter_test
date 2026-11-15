package it.faris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import it.faris.Notizia;

public class ArticleDetailController {

    @FXML private Label lblCategory;
    @FXML private Label lblMetaInfo;
    @FXML private Label lblTitle;
    @FXML private Label lblContent;
    @FXML private ImageView imgThumbnail;

    @FXML private Button btnBack;
    @FXML private Button btnAddNote;
    @FXML private Button btnDeepenAI;

    private BachecaLayoutController layoutController;
    private Notizia notiziaCorrente;

    public void setLayoutController(BachecaLayoutController layoutController) {
        this.layoutController = layoutController;
    }

    public void setNotizia(Notizia notizia) {
        this.notiziaCorrente = notizia;
        if (lblCategory != null) lblCategory.setText(notizia.getCategoria());
        if (lblMetaInfo != null) lblMetaInfo.setText(notizia.getDataPubblicazione() + " • " + notizia.getFonte());
        if (lblTitle != null) lblTitle.setText(notizia.getTitolo());
        if (lblContent != null) lblContent.setText(notizia.getContenutoCompleto());
        if (imgThumbnail != null && notizia.getUrlImmagine() != null) {
            imgThumbnail.setImage(new Image(notizia.getUrlImmagine(), true));
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        if (layoutController != null) {
            // Ripristina la bacheca centrale
            layoutController.loadBachecaView();
        }
    }

    @FXML
    private void handleOpenAIContext(ActionEvent event) {
        if (layoutController != null) {
            // Passa l'oggetto Notizia in visione al LayoutController
            layoutController.openAIAssistant(notiziaCorrente);
        }
    }

    @FXML
    private void handleAddNote(ActionEvent event) {
        if (layoutController != null) {
            layoutController.openAddNoteForm(notiziaCorrente);
        }
    }

    // --- CONTROLLI FINESTRA (Top Bar macOS) ---

    @FXML
    private void handleClose(ActionEvent event) {
        if (layoutController != null) {
            layoutController.loadBachecaView();
        }
    }

    @FXML
    private void handleMinimize(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }
}