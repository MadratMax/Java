package madrat;

import madrat.DataStructures.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Examples.usingBubbleSort();
        //Examples.usingList();
        //Examples.usingStack();

        //Examples.usingBinSearchTree();

        //Examples.usingMergeSort();

        Land land1 = new Land("land1", 200);
        Land land2 = new Land("land2", 122);
        Land land3 = new Land("land3", 33);
        Land land4 = new Land("land4", 444);
        Land land5 = new Land("land5", 680);
        Land land6 = new Land("land6", 100);
        Land land7 = new Land("land7", 10);
        Land land8 = new Land("land8", 999);
        Land land9 = new Land("land9", 999);
        Land land10 = new Land("land10", 999);
        Land land11 = new Land("land11", 999);
        Land land12 = new Land("land12", 999);
        Land land13 = new Land("land13", 999);
        Land land14 = new Land("land14", 999);

        List<Land> lands = new ArrayList<>();
        lands.add(land1);
        lands.add(land2);
        lands.add(land3);
        lands.add(land4);
        lands.add(land5);
        lands.add(land6);
        lands.add(land7);
        lands.add(land8);
        lands.add(land9);
        lands.add(land10);
        lands.add(land11);
        lands.add(land12);
        lands.add(land13);
        lands.add(land14);

        Graph<Integer> g = new Graph<Integer>();

        for (int i = 0; i<lands.size(); i++) {
            Random rand = new Random();
            Land randomLand = lands.get(rand.nextInt(lands.size()));
            if (randomLand != lands.get(i))
                g.addEdge(lands.get(i), randomLand, true);
        }

        System.out.println("Graph:\n"
                + g.toString());

        // Gives the no of vertices in the graph.
        g.getVertexCount();

        // Gives the no of edges in the graph.
        g.getEdgesCount(true);

        // Tells whether the edge is present or not.
        //g.hasEdge(3, 4);

        // Tells whether vertex is present or not
        //g.hasVertex(5);

    }
}
