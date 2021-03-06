import Ex2.src.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlgoritemTest {
    NodeData vertex = new NodeDataImpl(0, new GeoLocationImpl(1.19589389346247, 1.10152879327731, 0.0));
    NodeData vertex1 = new NodeDataImpl(1, new GeoLocationImpl(2.19589389346247, 2.10152879327731, 1.0));
    NodeData vertex2 = new NodeDataImpl(2, new GeoLocationImpl(3.19589389346247, 3.10152879327731, 2.0));
    NodeData vertex3 = new NodeDataImpl(3, new GeoLocationImpl(4.19589389346247, 4.10152879327731, 3.0));
    NodeData vertex4 = new NodeDataImpl(4, new GeoLocationImpl(5.19589389346247, 5.10152879327731, 4.0));
    NodeData vertex5 = new NodeDataImpl(5, new GeoLocationImpl(6.19589389346247, 6.10152879327731, 5.0));
    NodeData vertex6 = new NodeDataImpl(6, new GeoLocationImpl(7.19589389346247, 7.10152879327731, 6.0));
    NodeData vertex7 = new NodeDataImpl(7, new GeoLocationImpl(8.19589389346247, 8.10152879327731, 7.0));
    DirectedWeightedGraph graph = new DirectedWeightedGraphlmpl();
    DirectedWeightedGraphAlgorithms newGraph = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) this.graph);


    @Test
    public void constractorTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.connect(vertex.getKey(), vertex1.getKey(), 1.3118716362419698);
        graph.connect(vertex.getKey(), vertex2.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 3.123);
        graph.connect(vertex.getKey(), vertex2.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 3.123);
        Assertions.assertEquals(graph.getEdge(0, 1), new EdgeDataImpl(vertex.getKey(), vertex1.getKey(), 1.3118716362419698));

    }

    @Test
    public void isConnectTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.connect(vertex.getKey(), vertex1.getKey(), 1.3118716362419698);
        graph.connect(vertex.getKey(), vertex2.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 3.123);
        Assertions.assertEquals(newGraph.isConnected(), false);
    }

    @Test
    public void isConnectTrueTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);

        graph.connect(vertex.getKey(), vertex1.getKey(), 1.3118716362419698);
        graph.connect(vertex.getKey(), vertex2.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 3.123);
        graph.connect(vertex1.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex2.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex3.getKey(), vertex.getKey(), 3.123);
        Assertions.assertEquals(true, newGraph.isConnected());
    }

    @Test
    public void shortestPathDistTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.connect(vertex.getKey(), vertex1.getKey(), 2.0);
        graph.connect(vertex.getKey(), vertex2.getKey(), 2.0);
        graph.connect(vertex2.getKey(), vertex2.getKey(), 1.0);
        graph.connect(vertex1.getKey(), vertex3.getKey(), 1.0);
        graph.connect(vertex2.getKey(), vertex.getKey(), 1.0);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 2.0);
        graph.connect(vertex3.getKey(), vertex3.getKey(), 2.0);

        Assertions.assertEquals(3, newGraph.shortestPathDist(vertex.getKey(), vertex3.getKey()));
    }

    @Test
    public void shortestPathTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.addNode(vertex4);
        graph.addNode(vertex5);
        graph.addNode(vertex6);
        graph.addNode(vertex7);
        graph.connect(vertex.getKey(), vertex2.getKey(), 3.0);
        graph.connect(vertex.getKey(), vertex3.getKey(), 4.0);
        graph.connect(vertex3.getKey(), vertex4.getKey(), 4.0);
        graph.connect(vertex.getKey(), vertex1.getKey(), 3.0);
        graph.connect(vertex1.getKey(), vertex3.getKey(), 2.0);
        graph.connect(vertex1.getKey(), vertex4.getKey(), 3.0);
        graph.connect(vertex1.getKey(), vertex5.getKey(), 1.0);
        graph.connect(vertex2.getKey(), vertex4.getKey(), 1.0);
        graph.connect(vertex3.getKey(), vertex5.getKey(), 4.0);
        graph.connect(vertex4.getKey(), vertex5.getKey(), 2.0);
        graph.connect(vertex4.getKey(), vertex6.getKey(), 7.0);
        graph.connect(vertex4.getKey(), vertex7.getKey(), 2.0);
        graph.connect(vertex5.getKey(), vertex6.getKey(), 4.0);
        graph.connect(vertex6.getKey(), vertex7.getKey(), 5.0);
        LinkedList<NodeData> lst = new LinkedList();
        lst.add(vertex);

        DirectedWeightedGraphAlgorithms testSomethig = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);


        Assertions.assertEquals(lst, testSomethig.shortestPath(0, 0));
        LinkedList<NodeData> lst1 = new LinkedList();
        lst1.add(vertex1);
//        Assertions.assertEquals(lst1,newGraph1.shortestPath(0,1));
        List<NodeData> lst2 = new LinkedList();
        lst2.add(vertex);
        lst2.add(vertex2);
        List<NodeData> actual = testSomethig.shortestPath(0, 2);
        Assertions.assertEquals(lst2, actual);

    }

    @Test
    public void centerTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);

        graph.connect(vertex.getKey(), vertex1.getKey(), 1.3118716362419698);
        graph.connect(vertex.getKey(), vertex2.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex3.getKey(), 3.123);
        graph.connect(vertex1.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex2.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex3.getKey(), vertex.getKey(), 3.123);
        DirectedWeightedGraphAlgorithms newGraph = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        Assertions.assertEquals(newGraph.center(), vertex);


    }

    @Test
    public void tspTest() {
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.addNode(vertex4);
        graph.addNode(vertex5);
        graph.addNode(vertex6);
        graph.addNode(vertex7);

        graph.connect(vertex.getKey(), vertex1.getKey(), 1.3118716362419698);
        graph.connect(vertex1.getKey(), vertex.getKey(), 1.23);
        graph.connect(vertex1.getKey(), vertex2.getKey(), 2.13);
        graph.connect(vertex2.getKey(), vertex1.getKey(), 3.123);
        graph.connect(vertex2.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex3.getKey(), vertex2.getKey(), 3.123);
        graph.connect(vertex3.getKey(), vertex.getKey(), 3.123);
        graph.connect(vertex.getKey(), vertex4.getKey(), 3.123);
        DirectedWeightedGraphAlgorithms newGraph = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        List<NodeData> citis = new ArrayList<>();
        List<NodeData> temp = new ArrayList<NodeData>();
        temp.add(vertex2);
        temp.add(vertex);
        temp.add(vertex4);
        citis.add(vertex2);
        citis.add(vertex4);
        Assertions.assertEquals(temp, newGraph.tsp(citis));

    }

    @Test
    public void SaveAndLoadTest() {
        String path = "src\\Ex2\\data\\G1.json";
        DirectedWeightedGraphAlgorithms newGraph = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        newGraph.load(path);
        String path2 = "src\\Ex2\\data\\G1Test.json";
        newGraph.save(path2);

    }

    @Test
    public void CenterG1G2G3Test()

    {
        String path1 = "src\\Ex2\\data\\G1.json";
        DirectedWeightedGraphAlgorithms G1 = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        G1.load(path1);
        Assertions.assertEquals(G1.center().getKey(),8);
        String path2 = "src\\Ex2\\data\\G2.json";
        DirectedWeightedGraphAlgorithms G2 = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        G2.load(path2);
        Assertions.assertEquals(G2.center().getKey(),0);
        String path3 = "src\\Ex2\\data\\G3.json";
        DirectedWeightedGraphAlgorithms G3 = new DirectedWeightedGraphAlgorithmslmpl((DirectedWeightedGraphlmpl) graph);
        G3.load(path3);
        Assertions.assertEquals( G3.center().getKey(),40);

    }
    }


