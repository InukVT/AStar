package app.inuk;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;

public class AStarGraph {
    private ArrayList<Vertex> vertices;
    public AStarGraph() {
        vertices=new ArrayList<Vertex>();
    }
    public void addvertex(Vertex v) {
        vertices.add(v);
    }
    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }
    public boolean A_Star(Vertex start, Vertex destination, Heuristics heuristics)
    {   if (start==null || destination==null)
          return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        for (int i =0; i<vertices.size();i++)
        {
            // Reset the vertices changed values from last AStar
            vertices.get(i).reset();
            switch (heuristics)
            {
                case Euclidean:
                    vertices.get(1).seth(Euclidean(vertices.get(i),destination));
                    break;
                case Manhattan:
                    vertices.get(i).seth(Manhattan(vertices.get(i), destination));
                    break;
            }
        }
        start.setg(0.0);
        start.calculatef();
        //Start algorithm
        System.out.println("Start Algorithm");
        //Implement the Astar algorithm

        while (!Openlist.isEmpty())
        {
            // Take the topmost Vertex in cue and work on the
            Current = Openlist.remove();

            // Return true, because the destination was found
            if(Current == destination)
            {
                return true;
            }

            // Current has now been checked,
            // add it to the corresponding list.
            Closedlist.add(Current);

            CurrentNeighbors = Current.getNeighbours();

            // Check to see if we can get closer to end
            for ( int i=0;
                  i<CurrentNeighbors.size();
                  i++ )
            {
                Neighbor = CurrentNeighbors.get(i);
                // Check to see if this vertix is closer than earlier calcualted
                Double tempGOfV = Current.getg() + Current.getNeighbourDistance().get(i) ;

                // Out of the neighbors, find the one with lowest G
                if ( tempGOfV < Neighbor.getg() )
                {
                    // This will eventually be the path
                    Neighbor.setPrev(Current);

                    // because it is closer than earlier,
                    // update vertex to reflect this
                    Neighbor.setg(tempGOfV);

                    // For good measure, update F to reflect g chance
                    Neighbor.calculatef();

                    if (!Closedlist.contains(Neighbor) && !Openlist.contains(Neighbor) )
                    {
                        // Add neighbor to openlist
                        Openlist.offer(Neighbor);
                    } else if ( Openlist.contains(Neighbor) )
                    {
                        // Update position in openlist
                        Openlist.remove(Neighbor);
                        Openlist.offer(Neighbor);
                    }
                }
            }
        }
        // Because openlist has its head removed everytime,
        // and the loop goes as long as openlist is populated,
        // this return will be hit if no route could be found
        return false;
    }

    public Double Manhattan(Vertex from,Vertex goal){
        // Take the distance of the different positions
        double distX = abs ( from.getx() - goal.getx() );
        double distY = abs ( from.gety() - goal.gety() );

        return distX + distY;
    }

    public Double Euclidean( Vertex from,Vertex to){
        // Get the distance between positions
        double distX = abs ( from.getx() - to.getx() );
        double distY = abs ( from.gety() - to.gety() );

        // Calculate the square of the distance ( pythagoras )
        double sqrtDist = ( distX*distX ) + ( distY*distY ) ;

        // Return squareroot, alas the last part of pythagoras
        return sqrt(sqrtDist);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
}

class Vertex implements Comparable<Vertex>{
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
    }
    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }
    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return x; }
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}
    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex o) {
        // To sort the vertex in PriorityQueue, we need to have a reference. This sorts the Queue with F
        calculatef();
        o.calculatef();
        return getf().compareTo(o.getf());
   }

   // Custom string because .getid() got used a lot.
   @Override
    public String toString()
   {
       return this.id;
   }

    public void reset() {
        // Before the AStar alg has to run, it's a good idea to
        // reset all values from previous AStar run
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        prev = null;
    }
}
