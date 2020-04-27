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
            Current = Openlist.remove();

            if(Current == destination)
            {
                return true;
            }

            Closedlist.add(Current);

            CurrentNeighbors = Current.getNeighbours();

            for ( int i=0;
                  i<CurrentNeighbors.size();
                  i++ )
            {
                Neighbor = CurrentNeighbors.get(i);
                Double tempGOfV = Current.getg() + Current.getNeighbourDistance().get(i) ; // implement weight ( current, v )  (I think it's euclidean?

                if ( tempGOfV < Neighbor.getg() )
                {
                    // implement
                    Neighbor.setPrev(Current);

                    Neighbor.setg(tempGOfV);

                    Neighbor.calculatef();

                    if (!Closedlist.contains(Neighbor) && !Openlist.contains(Neighbor) )
                    {
                        Openlist.offer(Neighbor);
                    } else if ( Openlist.contains(Neighbor) )
                    {
                        Openlist.remove(Neighbor);
                        Openlist.offer(Neighbor);
                    }
                }

                // implement
            }
        }

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
        calculatef();
        o.calculatef();
        return getf().compareTo(o.getf());
   }

   @Override
    public String toString()
   {
       return this.id;
   }

    public void reset() {
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        prev = null;
    }
}
