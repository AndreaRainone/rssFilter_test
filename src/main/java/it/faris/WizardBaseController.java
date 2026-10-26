package it.faris;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.List;

public class WizardBaseController {

    @FXML private VBox contentArea;
    @FXML private Pane ind1, ind2, ind3, ind4;

    private final RegistrationViewModel viewModel = new RegistrationViewModel();

    @FXML
    public void initialize() {
        loadStep("/step1_account.fxml", 1);
    }

    public void loadStep(String fxmlPath, int stepIndex) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node stepNode = loader.load();

            WizardStep controller = loader.getController();
            if (controller != null) {
                controller.setup(this, viewModel);
            }

            contentArea.getChildren().setAll(stepNode);

            // Aggiorna l'avanzamento visivo delle pillole
            updateIndicators(stepIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateIndicators(int stepIndex) {
        List<Pane> indicators = List.of(ind1, ind2, ind3, ind4);
        for (int i = 0; i < indicators.size(); i++) {
            Pane pane = indicators.get(i);
            // Illumina le pillole degli step correnti e precedenti
            if (i < stepIndex) {
                pane.getStyleClass().setAll("step-indicator-active");
            } else {
                pane.getStyleClass().setAll("step-indicator");
            }
        }
    }

    @FXML
    private void closeApp() {
        ((Node) contentArea.getScene().getRoot()).getScene().getWindow().hide();
    }
}