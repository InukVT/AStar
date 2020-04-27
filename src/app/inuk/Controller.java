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
        heuristics.setValue(Heuristics.Euclidean);
        startVertex.setItems(FXCollections.observableArrayList(Main.MyMaze.getVertices()));
        startVertex.setValue(Main.MyMaze.getVertices().get(0));
        destination.setItems(FXCollections.observableArrayList(Main.MyMaze.getVertices()));

        destination.setOnAction(e -> {
            System.out.println("Start is " + startVertex.getValue());
            System.out.println("Destination is "+ destination.getValue());
            String path = aStar(startVertex.getValue(), destination.getValue(), heuristics.getValue());
            System.out.println(path);
            textArea.setText(path);
        });
        //printPath.setOnAction(e -> );
    }
    @FXML
    public void initialize(){
        placeHolder();
    }

    String aStar(Vertex from, Vertex to, Heuristics heuristics)
    {
        String path = "";
            if (Main.MyMaze.A_Star(from, to, heuristics)) {
                path += "Found a path\n";
                Vertex pvertex = from;
                Stack<Vertex> Path = new Stack<>();
            int limit = 0;
            while (pvertex != null) {
                Path.push(pvertex);
                pvertex = pvertex.getPrev();
            }
            if (!Path.isEmpty())
                limit = Path.size();
            for (int i = 0; i < limit - 1; i++)
                path += Path.pop().getid() + " - > ";
            if (limit > 0)
                path += Path.pop() ;

        } else {
            path += "DID NOT FIND A PATH!!";
        }
        path += "\n";
        return path;
    }
}
