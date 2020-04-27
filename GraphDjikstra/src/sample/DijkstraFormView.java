package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
public class DijkstraFormView {
    private GridPane Startview;
    private final AdjacencyGraph model;
    Button exitBtn = new Button("Exit");
    Label StartVertexLbl =new Label("Select start vertex:");
    ComboBox<Vertex> startVertexComB= new ComboBox<Vertex>();
    Button DijkstraBtn = new Button("Run Dijkstra from selected start");
    Label EndVertexLbl =new Label("Select destination:");
    ComboBox<Vertex> endVertexComB= new ComboBox<Vertex>();
    Button PrintBtn = new Button("Print shortest path");
    TextArea shortestpathTA=new TextArea();

    public DijkstraFormView(AdjacencyGraph GraphModel){
        this.model= GraphModel;
        Startview = new GridPane();
        Startview.setMinSize(300,200);
        Startview.setPadding(new Insets(10,10,10,10));
        Startview.setVgap(5);
        Startview.setHgap(1);
        ObservableList<Vertex> VertexList = FXCollections.observableArrayList(model.Vertices);
        Callback<ListView<Vertex>, ListCell<Vertex>> VertexcellFactory = new Callback<ListView<Vertex>, ListCell<Vertex>>() {
            @Override
            public ListCell<Vertex> call(ListView<Vertex> vertexListView) {
                return new ListCell<Vertex>(){
                    @Override
                    protected void updateItem(Vertex vertex, boolean empty) {
                        super.updateItem(vertex, empty);// to call method from parent class
                        if ( vertex ==null || empty){
                            setText(null);
                        }
                        else{
                            setText(vertex.Name);
                        }
                    }
                };
            }
        };
        startVertexComB.setItems(VertexList);
        startVertexComB.setButtonCell(VertexcellFactory.call(null));
        startVertexComB.setCellFactory(VertexcellFactory);
        startVertexComB.setValue(model.Vertices.get(0));
        endVertexComB.setItems(VertexList);
        endVertexComB.setButtonCell(VertexcellFactory.call(null));
        endVertexComB.setCellFactory(VertexcellFactory);
        endVertexComB.setValue(model.Vertices.get(0));
        shortestpathTA.setPrefColumnCount(1);
        //Add controls to pane
        Startview.add(StartVertexLbl, 1,1);
        Startview.add(startVertexComB,15,1);
        Startview.add(DijkstraBtn,15,2);
        Startview.add(EndVertexLbl,1,3);
        Startview.add(endVertexComB,15,3);
        Startview.add(PrintBtn,15,4);
        Startview.add(shortestpathTA,15,5);
        Startview.add(exitBtn,20,6);
    }
    public Parent asParent(){
        return Startview;
    }
}