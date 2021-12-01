package Ex2.src.api;

import Ex2.src.EdgeKey;

import java.util.*;

public class DirectedWeightedGraphlmpl implements DirectedWeightedGraph{

    // the vertices in the graph
    private Map<Integer, NodeData> vertices;
    // Here the vertices are stored with a direct edges to a vertex having the same key of the hash map
    private Map <Integer,List<NodeData>> neighborsNode;
    // here is the vertices that are neighbor of the vertices in the hash map value
    private Map <Integer,List<NodeData>> oppositeNeighborsNode;
    // here is the edges that of the graph, their key is their src and dist as I define in EdgeKey class.
    private Map <EdgeKey, EdgeData> edges;
    // numberOfEdge = counter of the edges in the graph
    //modeCount - counter of the Modifications in the graph
    // numberOfVertexes - counter of the Vertexes in the graph
    private int numberOfEdge, modeCount,numberOfVertexes;



    public DirectedWeightedGraphlmpl() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
        this.numberOfEdge = 0;
        this.modeCount = 0;
        this.neighborsNode = new HashMap<>();
        this.oppositeNeighborsNode = new HashMap<>();
        this.numberOfVertexes = 0;

    }
    // deep copy constructor.
    public DirectedWeightedGraphlmpl(DirectedWeightedGraphlmpl graph) {
        this.modeCount = graph.modeCount;
        this.numberOfEdge = graph.numberOfEdge;
        for(EdgeKey edgeKey : graph.edges.keySet()) {
            EdgeKey key = new EdgeKey(edgeKey.getSrc(),edgeKey.getDst());
            EdgeData newEdge = new EdgeDataImpl(graph.edges.get(key));
            this.edges.put(key, newEdge);
        }
        for (int key : graph.vertices.keySet()){
            NodeData newNode = new NodeDataImpl(graph.vertices.get(key));
            this.vertices.put(key,newNode);

        }
        for (int key1 : graph.neighborsNode.keySet()){

            List<NodeData> tmp1 = new ArrayList<>();
            for (int i = 0; i <graph.neighborsNode.get(key1).size(); i++) {
                tmp1.add(new NodeDataImpl(graph.neighborsNode.get(key1).get(i)));
            }
            this.neighborsNode.put(key1, tmp1);
        }
        for (int key2 : graph.oppositeNeighborsNode.keySet()){
            List<NodeData> tmp2 = new ArrayList<>();
            for(int i = 0; i < graph.getOppositeNeighborsNode().get(key2).size(); i++){
                tmp2.add(new NodeDataImpl(oppositeNeighborsNode.get(key2).get(i)));
            }
            this.oppositeNeighborsNode.put(key2, tmp2);

        }

    }

    // there I get the first number of the hashmap I define
    public  int getFirstKeyFromHashMap(){
        if(!this.edges.isEmpty()) {
            for (int key : this.neighborsNode.keySet()) {
                if ( !this.neighborsNode.get(key).isEmpty()) {
                    return key;
                }
            }
        }
        return -1; //there are no edges;
    }
    public Map <Integer,List<NodeData>> getNeighborsNode(){
        return this.neighborsNode;
    }
    public Map <Integer,List<NodeData>> getOppositeNeighborsNode(){
        return this.oppositeNeighborsNode;
    }
    public Map <Integer,NodeData> getVertices() {
        return this.vertices;
    }

    public Map <EdgeKey, EdgeData> getEdges(){
        return this.edges;
    }


    @Override
    public NodeData getNode(int key) {

        return vertices.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {

        return this.edges.get(new EdgeKey(src,dest));
    }

    @Override
    public void addNode(NodeData n) {
        if (n == null)
            return;
        if (n != null) {
            this.vertices.put(n.getKey(), n);
            // Add the node to neighborsNode and oppositeNeighborsNode too.
            this.neighborsNode.put(n.getKey(),new LinkedList<>());
            this.oppositeNeighborsNode.put(n.getKey(),new LinkedList<>());

            numberOfVertexes++;
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        //  check of the src and the dest are Equals
        if (src == dest) return;
        // check if the dest or src is null
        if (vertices.get(src) == null)
            return;
        if (vertices.get(dest) == null)
            return;
        // check if the Weight is Negative.
        if( w < 0)
            return;
        // if there is an edge run over it.
        if (this.getEdge(src,dest) != null){
            // put
            ((EdgeDataImpl) this.getEdge(src,dest)).setWeight(w);
              return;
        }
        // check if neighborsNode don't contain the src do:
        if(!this.neighborsNode.containsKey(src)){
            // Prepare empty list
            this.neighborsNode.put(src,new LinkedList<>());
        }
        // add to the src value the dest node
        this.neighborsNode.get(src).add(this.getNode(dest));
        // check if oppositeNeighborsNode don't contain the src do:
        if(!this.oppositeNeighborsNode.containsKey(dest)){
            // Prepare empty list
            this.oppositeNeighborsNode.put(dest,new LinkedList<>());
        }
        // add to the dest value the src node
        this.oppositeNeighborsNode.get(dest).add(this.getNode(src));
        EdgeData newEdge = new EdgeDataImpl(src,dest,w);
        // add edge to connect between src and dest.
        this.edges.put(new EdgeKey(src,dest),newEdge);
        // Adds 1 to modeCount and numberOfEdge
        this.modeCount++;
        this.numberOfEdge++;
       }


    @Override
    public Iterator<NodeData> nodeIter() {
        return this.vertices.values().iterator();

    }

    @Override
    public Iterator<EdgeData> edgeIter() {

        return this.edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        List<NodeData> nodes = this.neighborsNode.get(node_id);
        List<EdgeData> edges = new ArrayList<>();
        for(NodeData node : nodes){
            edges.add(this.getEdge(node_id, node.getKey()));
        }
        return edges.iterator();

    }

    @Override
    public NodeData removeNode(int key) {
        // insert the vertex value in node
        NodeData node = vertices.get(key);
        // check if vertices contain the node , if yes, remove it.
        if (this.vertices.containsKey(key)) {
            this.vertices.remove(key);
        }

        // check if neighborsNode contain the node
        if (this.neighborsNode.containsKey(key)) {
            // if it is remove its value from oppositeNeighborsNode
            for (int i = 0; i < this.neighborsNode.get(key).size(); i++) {
                NodeData tmp = this.neighborsNode.get(key).get(i);
                oppositeNeighborsNode.remove(i);
                this.removeEdge(key, tmp.getKey());
            }
            // remove the key from the neighborsNode
            this.neighborsNode.remove(key);
        }
        // check if oppositeNeighborsNode contain the node
        if (this.oppositeNeighborsNode.containsKey(key)) {
            // if it is remove its value from neighborsNode
            for (int i = 0; i < this.oppositeNeighborsNode.get(key).size(); i++) {
                NodeData tmp1 = this.oppositeNeighborsNode.get(key).get(i);
                neighborsNode.remove(i);
                this.removeEdge(tmp1.getKey(), key);

            } // remove the key from the oppositeNeighborsNode
                oppositeNeighborsNode.remove(key);

        }
        // Adds 1 to modeCount and return the node I had remove from the graph.
        this.modeCount++;
        return node;
    }



    @Override
    public EdgeData removeEdge(int src, int dest) {
        numberOfEdge--;

        return  this.edges.remove(getEdge(src,dest));

    }
    @Override
    public int nodeSize() {

        return this.vertices.size();
    }

    @Override
    public int edgeSize() {

        return this.numberOfEdge;
    }

    @Override
    public int getMC() {

        return this.modeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedWeightedGraphlmpl that = (DirectedWeightedGraphlmpl) o;
        return numberOfEdge == that.numberOfEdge && modeCount == that.modeCount && Objects.equals(vertices, that.vertices) && Objects.equals(neighborsNode, that.neighborsNode) && Objects.equals(oppositeNeighborsNode, that.oppositeNeighborsNode) && Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, neighborsNode, oppositeNeighborsNode, edges, numberOfEdge, modeCount);
    }

    public int getNumberOfVertexes (){
        return this.numberOfVertexes;
    }
}
