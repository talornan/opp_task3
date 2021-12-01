package Ex2.src.api;

import java.util.List;

public class Graph {
    List<Node> Nodes;
    List<Edge> Edges;

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.Nodes = nodes;
        this.Edges = edges;
    }

    public List<Node> getNodes() {
        return Nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.Nodes = nodes;
    }

    public List<Edge> getEdges() {
        return Edges;
    }

    public void setEdges(List<Edge> edges) {
        this.Edges = edges;
    }
}
