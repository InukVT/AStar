package app.inuk;
import java.util.Stack;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
public class Controller {
    @FXML
    private ComboBox<?> startVertex;

    @FXML
    private ComboBox<?> destination;

    @FXML
    private ComboBox<?> heuristics;

    @FXML
    private Button printPath;
}
