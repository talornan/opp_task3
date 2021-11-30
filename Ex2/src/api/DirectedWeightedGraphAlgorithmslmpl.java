package Ex2.src.api;

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
        for(int key :graph.getVertices().keySet()){
            for (int nd : graph.getNeighborsNode().keySet()) {
                NodeDataImpl node = (NodeDataImpl) graph.getNode(nd);
                node.setVisit(false);
            }
            if(isStronglyConnected(graph, key) == false){
                return false;
            }
        }
        return true;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        if (this.graph.getNode(dest) == null || this.graph.getNode(src) == null)
            return -1;
        NodeData destintion = this.graph.getNode(dest);
        dijkstra(this.graph,(NodeDataImpl) this.graph.getNode(src));
        double path = destintion.getWeight();
        if (destintion.getWeight() == Integer.MAX_VALUE) {
            return -1;
        }
        return path;
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> path = new LinkedList<>();
        if (src == dest) {
            path.add(graph.getNode(src));
            return path;
        }
        if (graph.getNode(dest) == null || graph.getNode(src) == null)
            return path;
        path.add(graph.getNode(src));
        dijkstra(graph,(NodeDataImpl) this.graph.getVertices().get(src));
        NodeDataImpl distance = (NodeDataImpl)  graph.getNode(dest);
        while ((Integer) distance.getFrom() != null) {
             path.add(0, distance);
             distance = (NodeDataImpl)  graph.getNeighborsNode().get((distance.getFrom()));
        }
        path.add(0, distance);
        if (!path.contains(graph.getNode(src))) {
            return null;
        }
        return path;
    }

    @Override
    public NodeData center() {

        return null;
//        if(!isConnected()){
//            return null;
//        }
//        else {
//            double [] array = new double[graph.getNumberOfVertexes()];
//            for (int i = 0; i < graph.getNumberOfVertexes(); i++){
//                int node = graph.getFirstKeyFromHashMap();
//                array[i]=shortestPathDist(node,i);
//            }
//            double averge = 0;
//            for (int i = 0; i < array.length; i++){
//                averge+=array[i];
//            }
//            averge = averge/array.length;
//
//            for(int i = 0; i < array.length; i++){
//                if(averge == array[i])
//                    return graph.getNode(i);
//            }
//
//        }
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    private void dijkstra(DirectedWeightedGraphlmpl graph , NodeDataImpl src) {
        src.setFrom(0);
        PriorityQueue <NodeDataImpl> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(src);
        src.setVisit(true);
        for(NodeData  node : graph.getVertices().values()){
            node.setWeight(Integer.MAX_VALUE);
            ((NodeDataImpl) node).setFrom(null);
            ((NodeDataImpl) node).setVisit(false);
            if(node!= src)
            priorityQueue.add((NodeDataImpl) node);
        }
        src.setWeight(0);
        while (!priorityQueue.isEmpty()) {
            NodeDataImpl cur =  priorityQueue.remove();
                for (int i = 0; i < graph.getNeighborsNode().get(cur.getKey()).size(); i++) {
                    NodeDataImpl nei =  (NodeDataImpl) graph.getNeighborsNode().get(cur.getKey()).get(i);
                        double edgeWeight =  graph.getEdge(cur.getKey(),nei.getKey()).getWeight();
                        double newDistance = cur.getWeight() + edgeWeight;
                        if (nei.getWeight() > newDistance) {
                            nei.setWeight(newDistance);
                            nei.setFrom(cur.getKey());
                }
            }
        }
    }


    private static void DFS(DirectedWeightedGraphlmpl graph,NodeDataImpl node)
    {
        node.setVisit(true);

        for (NodeData curr: graph.getNeighborsNode().get(node.getKey()))
        {
            NodeDataImpl temp = (NodeDataImpl) curr;
            if(!temp.getNodeVis()){
                DFS(graph, temp);

            }
        }

    }



    public static boolean isStronglyConnected(DirectedWeightedGraphlmpl graph,int key) {
            NodeDataImpl node = (NodeDataImpl)graph.getNode(key);
            DFS(graph, node);
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



