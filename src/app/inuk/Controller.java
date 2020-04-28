package app.inuk;
import java.util.Stack;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

    // Initialise the views.
    // Default value to minimise need of null checking.
    // Commented out setOnActions, uncomment these if you want stuff to happen
    // as soon as you update their values.
    @FXML
    public void initialize(){
        generateVertex();

        heuristics.getItems().setAll(Heuristics.values());
        heuristics.setValue(Heuristics.Euclidean);
        //heuristics.setOnAction(e -> action(e));

        startVertex.setItems(FXCollections.observableArrayList(MyMaze.getVertices()));
        startVertex.setValue(MyMaze.getVertices().get(0));
        //startVertex.setOnAction(e -> action(e));

        destination.setItems(FXCollections.observableArrayList(MyMaze.getVertices()));
        destination.setValue(MyMaze.getVertices().get(0));
        //destination.setOnAction(e -> action(e));

        printPath.setOnAction(e -> action(e));
    }

    // Print out the found path, with STD out debug values.
    private void action(ActionEvent e)
    {
        System.out.println("Start is " + startVertex.getValue());
        System.out.println("Destination is "+ destination.getValue());
        System.out.println("by " + heuristics.getValue());
        String path = aStar(startVertex.getValue(), destination.getValue(), heuristics.getValue());
        System.out.println(path);
        textArea.setText(path + "by " + heuristics.getValue());
    }

    private String aStar(Vertex from, Vertex to, Heuristics heuristics)
    {
        // This string is the path found
        String path = "";
        // Check to see if a path is even found
        if (MyMaze.A_Star(from, to, heuristics)) {
            path += "Found a path\n";
            Vertex pvertex = to;
            Stack<Vertex> Path = new Stack<>();
            // Take the path found in AStar and turn it to a Path usable here
            // This path is in reverse order compared to the path in AStar,
            // as the AStar path goes from end node to start node.
            while (pvertex != null) {
                Path.push(pvertex);
                pvertex = pvertex.getPrev();
            }
            while (!Path.isEmpty()) {
                // Print the node
                path += Path.pop();
                // If this is not the last part of the path, then print an arrow
                if (Path.size() > 0 )
                {
                  path += " - > ";
                }
            }
        } else {
            path += "DID NOT FIND A PATH!!";
        }
        path += "\n";
        return path;
    }

    AStarGraph MyMaze = new AStarGraph();

    // Make the graph provided to you in the diagram and table
    //The vertices must be constructed like A
    Vertex A = new Vertex("A", 0, 4);
    Vertex B = new Vertex("B", 1, 7);
    Vertex C = new Vertex("C", 4, 0);
    Vertex D = new Vertex("D", 3, 7);
    Vertex E = new Vertex("E", 3, 3);
    Vertex F = new Vertex("F", 6, 6);
    Vertex G = new Vertex("G", 7, 2);
    Vertex H = new Vertex("H", 8, 7);
    Vertex I = new Vertex("I", 9, 2);
    Vertex J = new Vertex("J", 11, 5);

    void generateVertex()  {
        System.out.println("Generating maze");

        MyMaze.addvertex(A);
        MyMaze.addvertex(B);
        MyMaze.addvertex(C);
        MyMaze.addvertex(D);
        MyMaze.addvertex(E);
        MyMaze.addvertex(F);
        MyMaze.addvertex(G);
        MyMaze.addvertex(H);
        MyMaze.addvertex(I);
        MyMaze.addvertex(J);

        MyMaze.newconnection(A, B, 3.41);
        MyMaze.newconnection(A, C, 6.82);
        MyMaze.newconnection(B, D, 2.0);
        MyMaze.newconnection(C, G, 4.41);
        MyMaze.newconnection(C, I, 4.82);
        MyMaze.newconnection(D, E, 4.0);
        MyMaze.newconnection(E, F, 6.23);
        MyMaze.newconnection(F, G, 4.41);
        MyMaze.newconnection(F, H, 3.82);
        MyMaze.newconnection(G, H, 5.41);
        MyMaze.newconnection(G, I, 2.82);
        MyMaze.newconnection(H, J, 4.41);
        MyMaze.newconnection(I, J, 3.82);
    }
}
