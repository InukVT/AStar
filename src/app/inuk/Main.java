package app.inuk;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        AStarGraph MyMaze = new AStarGraph();
        // Make the graph provided to you in the diagram and table
        //The vertices must be constructed like A
        Vertex A=new Vertex("A",0,4);
        Vertex J=null; //This must be changed

        if(MyMaze.A_Star(A,J))
        {
            System.out.println("Found a path");
            Vertex pvertex=J;
            Stack<Vertex> Path = new Stack<>();
            int limit=0;
            while (pvertex!=null)
            {
                Path.push(pvertex);
                pvertex=pvertex.getPrev();
            }
            if(!Path.isEmpty())
                 limit =Path.size();
            for(int i=0;i<limit-1;i++)
                System.out.print(Path.pop().getid() +" - > ");
            if (limit>0)
                System.out.println(Path.pop().getid());

        }
        else
            System.out.println("DID NOT FIND A PATH!!");

    }
}
