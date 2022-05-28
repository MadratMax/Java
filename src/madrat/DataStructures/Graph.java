package madrat.DataStructures;

import madrat.Empire.Land;
import madrat.Empire.MessageBox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    // We use Hashmap to store the edges in the graph
    private Map<Land, List<Land>> map = new HashMap<>();

    // This function adds a new vertex to the graph
    public void addVertex(Land s)
    {
        map.put(s, new LinkedList<Land>());
    }

    // This function adds the edge
    // between source to destination
    public void addEdge(Land source,
                        Land destination,
                        boolean bidirectional)
    {

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(source);
        }
    }

    // This function gives the count of edges
    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (Land v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
    }

    // This function gives whether an edge is present or not.
    public void hasEdge(T s, T d)
    {
        if (map.get(s).contains(d)) {
            System.out.println("The graph has an edge between "
                    + s + " and " + d + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                    + s + " and " + d + ".");
        }
    }

    public void setNeighbours()
    {
        StringBuilder builder = new StringBuilder();

        for (Land v : map.keySet()) {
            builder.append(v.name().toString() + ": ");
            for (Land w : map.get(v)) {
                builder.append(w.name().toString() + " ");
                v.setNeighbour(w);
            }
            builder.append("\n");
        }

        MessageBox.pushMessage("Map: \n" + builder.toString());
    }
}