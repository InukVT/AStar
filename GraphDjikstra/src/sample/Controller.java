package sample;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import java.util.Stack;
public class Controller {
    private final AdjacencyGraph model;
    private final DijkstraFormView view;
    public Controller(AdjacencyGraph GraphModel, DijkstraFormView DijkstraView) {
        this.model = GraphModel;
        this.view = DijkstraView;
        view.exitBtn.setOnAction(e -> Platform.exit());
        view.DijkstraBtn.setOnAction(e -> model.Dijkstra(view.startVertexComB.getValue()));
        EventHandler<ActionEvent> PrintRequestHndl
                = e->HandlePrintRequest(view.endVertexComB.getValue(),view.shortestpathTA);
        view.PrintBtn.setOnAction(PrintRequestHndl);
    }
    public void HandlePrintRequest(Vertex destination, TextArea TArea) {
        Vertex pvertex = destination;
        TArea.appendText("To " + destination.Name + " Shortest length: " + destination.distance+"\n");
        Stack<Vertex> Path = new Stack<>();
        int limit =0;
        while (pvertex!=null)
        {
            Path.push(pvertex);
            pvertex = pvertex.prev;
        }
        if (!Path.isEmpty())
            limit = Path.size();
        for (int i=0; i<limit -1; i++)
            TArea.appendText(Path.pop().Name + " -> \n");
        if (limit >0)
            TArea.appendText(Path.pop().Name+ "\n");
    }
}
