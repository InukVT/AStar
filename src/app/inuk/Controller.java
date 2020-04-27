package app.inuk;
import java.util.Stack;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.print.attribute.standard.Destination;

public class Controller {
    @FXML
    private ComboBox<Vertex> startVertex;

    @FXML
    private ComboBox<Vertex> destination;

    @FXML
    private ComboBox<Heuristics> heuristics;

    @FXML
    private Button printPath;

    @FXML
    private TextArea textArea;

    public void placeHolder(){
        heuristics.getItems().setAll(Heuristics.values());
        startVertex.setItems(FXCollections.observableArrayList(Main.MyMaze.getVertices()));
        destination.setItems(FXCollections.observableArrayList(Main.MyMaze.getVertices()));
        //printPath.setOnAction(e -> );
    }
    @FXML
    public void initialize(){
        placeHolder();
    }
}
