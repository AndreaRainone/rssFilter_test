package it.faris;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.util.Duration;

public class WizardBaseController {
    @FXML private StackPane contentArea;
    @FXML private Pane ind1, ind2, ind3, ind4;

    private RegistrationViewModel viewModel = new RegistrationViewModel();
    private Pane[] indicators;

    @FXML
    public void initialize() {
        indicators = new Pane[]{ind1, ind2, ind3, ind4};
        loadStep("/step1_account.fxml", 1);
    }

    // Il metodo ora accetta il numero dello step (1, 2, 3 o 4)
    public void loadStep(String fxmlFile, int stepNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node stepNode = loader.load();

            WizardStep controller = loader.getController();
            if (controller != null) {
                controller.setup(this, viewModel);
            }

            // Aggiorna visivamente gli indicatori (Pillole)
            updateIndicators(stepNumber);

            // Effetto dissolvenza premium
            stepNode.setOpacity(0);
            contentArea.getChildren().setAll(stepNode);
            FadeTransition ft = new FadeTransition(Duration.millis(300), stepNode);
            ft.setToValue(1.0);
            ft.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateIndicators(int currentStep) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].getStyleClass().removeAll("step-indicator", "step-indicator-active");
            if (i < currentStep) {
                indicators[i].getStyleClass().add("step-indicator-active");
            } else {
                indicators[i].getStyleClass().add("step-indicator");
            }
        }
    }

    @FXML
    private void closeApp() {
        System.exit(0);
    }
}