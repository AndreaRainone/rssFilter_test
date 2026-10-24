import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginForm extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Scene scene1 = new Scene(loader.load());

        // 1. Rimuove la barra di Windows e rende trasparente la finestra
        stage.initStyle(StageStyle.TRANSPARENT);
        scene1.setFill(Color.TRANSPARENT);

        // 2. Permette di trascinare la card con il mouse
        scene1.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        scene1.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.setScene(scene1);
        stage.show();
    }

}