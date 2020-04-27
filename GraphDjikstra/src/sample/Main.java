package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        AdjacencyGraph GraphModel =CreateGraph();
        DijkstraFormView Dijkstraview=new DijkstraFormView(GraphModel);
        Controller controller= new Controller(GraphModel,Dijkstraview);
        primaryStage.setTitle("Dijkstra Form");
        primaryStage.setScene(new Scene(Dijkstraview.asParent(), 400, 300));
        primaryStage.show();
    }

    public AdjacencyGraph CreateGraph() {
        AdjacencyGraph adjacencyGraph = new AdjacencyGraph();
        Vertex a = new Vertex("1");
        adjacencyGraph.addvertex(a);
        Vertex b = new Vertex("2");
        adjacencyGraph.addvertex(b);
        Vertex c = new Vertex("3");
        adjacencyGraph.addvertex(c);
        Vertex d = new Vertex("4");
        adjacencyGraph.addvertex(d);
        Vertex e = new Vertex("5");
        adjacencyGraph.addvertex(e);
        Vertex f = new Vertex("6");
        adjacencyGraph.addvertex(f);
        Vertex g = new Vertex("7");
        adjacencyGraph.addvertex(g);
        adjacencyGraph.newedge(a, b, 1);
        adjacencyGraph.newedge(a, c, 5);
        adjacencyGraph.newedge(a, e, 3);
        adjacencyGraph.newedge(b, e, 1);
        adjacencyGraph.newedge(b, f, 7);
        adjacencyGraph.newedge(c, d, 1);
        adjacencyGraph.newedge(d, e, 1);
        adjacencyGraph.newedge(d, g, 1);
        adjacencyGraph.newedge(e, c, 1);
        adjacencyGraph.newedge(e, d, 3);
        adjacencyGraph.newedge(e, f, 3);
        adjacencyGraph.newedge(e, g, 4);
        adjacencyGraph.newedge(f, g, 1);
        adjacencyGraph.printGraph();
        return adjacencyGraph;
    }
}
