/*
	This Program Was Written By:
	Joshua Tyler Bandy
*/


//Imports
import java.util.*;

public class Main{

    //Declarations
    static int cities, roads, capital, distTres;
    static int citieTres = 0, roadTres = 0;

    public static void main(String[] args) {
        //Declarations
        Scanner scanIn = new Scanner(System.in);

        cities = scanIn.nextInt();
        roads = scanIn.nextInt();
        capital = scanIn.nextInt();

        List<Edge> edges = new ArrayList<>();

        // Read in roads
        for(int i = 0; i < roads; i++){
            int u = scanIn.nextInt();
            int v = scanIn.nextInt();
            int w = scanIn.nextInt();
            edges.add(new Edge(u, v, w));
        }

        distTres = scanIn.nextInt();//Get distance

       //More Declarations
        Graph graph = new Graph(edges, cities);//Create Graph
        

        for(int source = 0; source < cities; source++){
            findShortestPaths(graph, source, cities);
        }
        
        System.out.println("In city: " + citieTres);
        System.out.println("On the road: " + roadTres);

        scanIn.close();
    }

    public static void findShortestPaths(Graph graph, int source, int n){
        //Create a min heap and push source node having distance 0
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(node->node.weight));

        // set initial distance from the source as infinity
        List<Integer> dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));

        dist.set(source, 0);//Distance from the source to itself is zero
        minHeap.add(new Node(source, 0));

        //Boolean array to track vertices for which min cost is found
        boolean[] done = new boolean[cities];
        done[source] = true;

        while(!minHeap.isEmpty()){
            Node node = minHeap.poll();// Remove and ret the best vertex

            int u = node.vertex;// Get vertex num

            for(Edge edge: graph.adjList.get(u)){
                int v = edge.dest;
                int weight = edge.weight;

                if(!done[v] && (dist.get(u) + weight) < dist.get(v)){
                    dist.set(v, dist.get(u) + weight);
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }
            done[u] = true;
        }

        for(int i = 0; i < cities; i++){
            if(dist.get(i) == distTres){
                citieTres++;
            }
        }

       /*  for(int i = 0; i < cities; i++){
            for(Edge edge: graph.adjList.get(i)){
                int v = edge.dest;
                int weight = edge.weight;
                
                if(i < v){//ensuer we don't use the same road twice
                    if(dist.get(i) < distTres && distTres < dist.get(i) + weight && dist.get(v) > distTres){
                    roadTres++;
                    }
                    if(dist.get(v) < distTres && distTres < dist.get(v) + weight && dist.get(i) > distTres ){
                      roadTres++;
                    }
                }
            }
        } */
    }

    static class Graph{
        // A  list of Lists
        List<List<Edge>> adjList = null;

        //Constructor
        Graph(List<Edge> edges, int n){
            adjList = new ArrayList<>();

            for(int i = 0; i < n; i++){
                adjList.add(new ArrayList<>());
            }

            for(Edge edge: edges){
                adjList.get(edge.source).add(edge);
            }
        }
    }

    //Stores a graph's edge
    static class Edge{
        int source, dest, weight;

        public Edge(int source, int dest, int weight){
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    //Store a heap node
    static class Node{
        int vertex, weight;

        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}