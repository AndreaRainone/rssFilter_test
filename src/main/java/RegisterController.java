import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RegisterController extends Application {

    @FXML
    private void chiudiFinestra() {
        System.exit(0);
    }

    @FXML
    private void apriLogin() { loginForm.launch();}

    @Override
    public void start(Stage stage) throws Exception {
        loginForm.launch();
    }
}
