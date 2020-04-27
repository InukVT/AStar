package app.inuk;

import java.util.Stack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage primaryStage) throws Exception {
        generateVertex();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("A-star");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public static AStarGraph MyMaze;
    public void generateVertex()  {
        MyMaze = new AStarGraph();
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

        if (MyMaze.A_Star(A, J, Heuristics.Manhattan)) {
            System.out.println("Found a path");
            Vertex pvertex = J;
            Stack<Vertex> Path = new Stack<>();
            int limit = 0;
            while (pvertex != null) {
                Path.push(pvertex);
                pvertex = pvertex.getPrev();
            }
            if (!Path.isEmpty())
                limit = Path.size();
            for (int i = 0; i < limit - 1; i++)
                System.out.print(Path.pop().getid() + " - > ");
            if (limit > 0)
                System.out.println(Path.pop().getid());

        }
        else
            System.out.println("DID NOT FIND A PATH!!");

    }
}
