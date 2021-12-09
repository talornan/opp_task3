package Ex2.src.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DirectedWeightedGraphAlgorithmslmpl implements DirectedWeightedGraphAlgorithms{
    DirectedWeightedGraphlmpl graph;
    public DirectedWeightedGraphAlgorithmslmpl(DirectedWeightedGraphlmpl g) {
        this.graph = g;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        graph = (DirectedWeightedGraphlmpl) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {

        DirectedWeightedGraph copy = new DirectedWeightedGraphlmpl(this.graph);
        return copy;

    }

    @Override
    public boolean isConnected() {
        // go over Vertices map
        for(int key :graph.getVertices().keySet()){
            // go over NeighborsNode map
            for (int nd : graph.getNeighborsNode().keySet()) {
                // define node as the value of nd
                NodeDataImpl node = (NodeDataImpl) graph.getNode(nd);
                // define the node as not visited
                node.setVisit(false);
            }
            // check if the graph is connected by isStronglyConnected function.
            if(isStronglyConnected(graph, key) == false){
                return false;
            }
        }
        return true;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        // check if src and dest is equals
        if (src == dest)
            return 0;
        // check if src and dest have a value
        if (this.graph.getNode(dest) == null || this.graph.getNode(src) == null)
            return -1;
        // create node for contain the dest


        DirectedWeightedGraphlmpl graphCopy = new DirectedWeightedGraphlmpl(this.graph);

        NodeData destintion = graphCopy.getNode(dest);
        // use dijkstra function to save the path from src

        dijkstra(graphCopy,(NodeDataImpl) graphCopy.getNode(src));
        // check  if there is a path if it is he return it's if not return -1.
        double path = destintion.getWeight();
        if (destintion.getWeight() == Integer.MAX_VALUE) {
            return -1;
        }
        return path;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        // define a new list to contain the path
        List<NodeData> path = new LinkedList<>();
        // check if src and dest equals if yes, add src value to path list and return the list.
        if (src == dest) {
            path.add(graph.getNode(src));
            return path;
        }
        // check if src and dest have a value, if not return empty list.
        if (graph.getNode(dest) == null || graph.getNode(src) == null)
            return path;
        // activate dijkstra function to get the shortest Path and add the path I get to path list.
        dijkstra(graph,(NodeDataImpl) this.graph.getVertices().get(src));
        NodeDataImpl distance = (NodeDataImpl)  graph.getNode(dest);
        while (distance.getKey() != src) {
             path.add(0, distance);
             distance = (NodeDataImpl)  graph.getNode(distance.getFrom());
        }
        // add src to the path and return the path.
        path.add(0, graph.getNode(src));
        return path;
    }

    @Override
    public NodeData center() {
        // check if the graph is connected , if not return null.
        if(!isConnected()){
            return null;
        }
        // define a new map
       Map <Integer, Double> distance = new HashMap<>();
        // go over Vertices value and add for every vertex is distance from all the other vertex's.
        for(NodeData node : graph.getVertices().values()){
            DirectedWeightedGraphlmpl graphCopy = new DirectedWeightedGraphlmpl(this.graph);
            NodeData copyNode = graphCopy.getNode(node.getKey());
           double dis = Double.MIN_VALUE;
           dijkstra(graph, (NodeDataImpl) copyNode);
           for(NodeData n : graph.getVertices().values()){
                double shortest  = n.getWeight();
                dis = Math.max(dis, shortest);
           }

           // put in distance the dis of the one vertex from the other's.
           distance.put(node.getKey(),dis);
        }
       // check what node have the shortest path from the other by compare the value's is in the map
        double minValue = Double.MAX_VALUE;
        NodeData tempNode =null;
       for (NodeData node : graph.getVertices().values()){
           if(distance.get(node.getKey()) < minValue){
               minValue = distance.get(node.getKey());
               tempNode = node;
           }
       }
       return tempNode;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        // define a new list to contain the path
        List<NodeData> tcp = new ArrayList<>();
        // do over Vertices of the graph and update visit status to false.
        for(NodeData node : this.graph.getVertices().values()){
            ((NodeDataImpl) node).setVisit(false);
        }
        // if there is no value's in cities return empty list
        if(cities.size() == 0){
            return tcp;
        }
        // define a new node as the value of cities list in the first place
        NodeDataImpl prev = ((NodeDataImpl) cities.get(0));
        // update prev visit status to true.
        prev.setVisit(true);
        // add to  tcp prev cities in the first place
        tcp.add(cities.get(0));
        // go over cities size and go in the shortest Path from prev to curr vertex, I update all the vertex i pass as visited and not go over them again.
        for(int i=1; i < cities.size(); i++){
            NodeDataImpl curr = ((NodeDataImpl) cities.get(i));
            if(curr.getNodeVis() == false){
                List<NodeData> res = this.shortestPath(prev.getKey(), curr.getKey());
                for(NodeData node : res){
                    if(node.getKey() != prev.getKey()){
                        ((NodeDataImpl )node).setVisit(true);

                        tcp.add(node);
                    }
                }
                prev = curr;
            }
        }
        return tcp;
    }

    @Override
    public boolean save(String file) {

       List<Node> nodes = new ArrayList<>();
       Iterator<NodeData> nodeIterator = graph.nodeIter();
       while (nodeIterator.hasNext()){
           NodeData node = nodeIterator.next();
           String pos = node.getLocation().x() + "," + node.getLocation().y() + ","+ node.getLocation().z();
           nodes.add(new Node(node.getKey(), pos));

       }
        List<Edge> edges = new ArrayList<>();
        Iterator<EdgeData> edgeIterator = graph.edgeIter();
        while (edgeIterator.hasNext()){
            EdgeData edge = edgeIterator.next();
            edges.add(new Edge(edge.getSrc(),edge.getWeight(), edge.getDest()));

        }
        Graph graph = new Graph(nodes, edges);
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(graph, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }
    @Override
    public boolean load(String file) {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(file));
            Gson gson = new Gson();

            // convert JSON file to map
            DirectedWeightedGraphlmpl newGraph = new DirectedWeightedGraphlmpl();

            Graph graph = gson.fromJson(reader, Graph.class);
            for(Node node : graph.getNodes()){
                String[] pos = node.getPos().split(",");
                newGraph.addNode(new NodeDataImpl(node.getId(), new GeoLocationImpl(Double.valueOf(pos[0]), Double.valueOf(pos[0]), Double.valueOf(pos[0]))));
            }
            for(Edge edge : graph.getEdges()){
                newGraph.connect(edge.getSrc(),edge.getDest(),edge.getW());
            }
            this.graph = newGraph;

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void dijkstra(DirectedWeightedGraphlmpl graph , NodeDataImpl src) {
        // define PriorityQueue .
        PriorityQueue <NodeDataImpl> priorityQueue = new PriorityQueue<>();
        // update src visit status to true
        src.setVisit(true);
        // go over Vertice and update their From statue to null and their Visit statue as false.
        for(NodeData  node : graph.getVertices().values()){
            node.setWeight(Integer.MAX_VALUE);
            ((NodeDataImpl) node).setFrom(null);
            ((NodeDataImpl) node).setVisit(false);
            priorityQueue.add((NodeDataImpl) node);
        }
        src.setWeight(0);
        // Add to  PriorityQueue src .
        priorityQueue.remove(src);
        priorityQueue.add(src);
        // if priorityQueue not empty go over cur NeighborsNode and
            // check if neighbor Weight bigger than edgeWeight + cur Weight
        while (!priorityQueue.isEmpty()) {
            NodeDataImpl cur =  priorityQueue.remove();
                for (int i = 0; i < graph.getNeighborsNode().get(cur.getKey()).size(); i++) {
                    NodeDataImpl nei =  (NodeDataImpl) graph.getNeighborsNode().get(cur.getKey()).get(i);
                    double edgeWeight =  graph.getEdge(cur.getKey(),nei.getKey()).getWeight();
                    double newDistance = cur.getWeight() + edgeWeight;
                    if (nei.getWeight() > newDistance) {
                        nei.setWeight(newDistance);
                        if(priorityQueue.contains(nei)){
                            priorityQueue.remove(nei);
                            priorityQueue.add(nei);
                        }
                        nei.setFrom(cur.getKey());
                }
            }
        }
        return;
    }

// this function is base on BFS algo
    private static void BFS(DirectedWeightedGraphlmpl graph,NodeDataImpl node) {
        node.setVisit(true);
        Queue<NodeData> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            NodeData currNode= queue.poll();
            for (NodeData curr: graph.getNeighborsNode().get(currNode.getKey())){
                NodeDataImpl temp = (NodeDataImpl) curr;
                if(!temp.getNodeVis()){
                    temp.setVisit(true);
                    queue.add(curr);

                }
            }
        }
    }


    // this function check by dfs algo if the graph is Strongly Connected
    public static boolean isStronglyConnected(DirectedWeightedGraphlmpl graph,int key) {
            NodeDataImpl node = (NodeDataImpl)graph.getNode(key);
            BFS(graph, node);
            for (NodeData b :  graph.getVertices().values()) {
                NodeDataImpl tmp = (NodeDataImpl) b;
                if (!tmp.getNodeVis()) {
                    return false;
                    }
            }

        return true;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedWeightedGraphAlgorithmslmpl that = (DirectedWeightedGraphAlgorithmslmpl) o;
        return Objects.equals(graph, that.graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph);
    }


}



