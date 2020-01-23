package lesson7;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(10);

        graph.addEdge(0,5);
        graph.addEdge(0,6);
        graph.addEdge(0,9);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,4);
        graph.addEdge(2,7);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(4,6);
        graph.addEdge(6,8);
        graph.addEdge(7,8);
        graph.addEdge(8,9);


        BreadthFirstPath bfp = new BreadthFirstPath(graph, 1);

        LinkedList<Integer> path = bfp.pathTo(3);
        System.out.println(path + " length = " + path.size());
        path = bfp.pathTo(5);
        System.out.println(path + " length = " + path.size());
        path = bfp.pathTo(0);
        System.out.println(path + " length = " + path.size());
        path = bfp.pathTo(8);
        System.out.println(path + " length = " + path.size());

    }
}
