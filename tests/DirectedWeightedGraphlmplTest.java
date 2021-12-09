import Ex2.src.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphlmplTest {
    EdgeData edge = new EdgeDataImpl( 2,5,1.3118716362419698);
    EdgeData edge1 = new EdgeDataImpl( 3,2,2.3118716362419698);
    EdgeData edge2 = new EdgeDataImpl( 1,4,0.1356);
    NodeData vertex = new NodeDataImpl(0,new GeoLocationImpl(1.19589389346247,1.10152879327731,0.0));
    NodeData vertex1 = new NodeDataImpl(1,new GeoLocationImpl(2.19589389346247,2.10152879327731,1.0));
    NodeData vertex2 = new NodeDataImpl(2,new GeoLocationImpl(3.19589389346247,3.10152879327731,2.0));
    NodeData vertex3 = new NodeDataImpl(3,new GeoLocationImpl(4.19589389346247,4.10152879327731,3.0));
    NodeData vertex4 = new NodeDataImpl(4,new GeoLocationImpl(5.19589389346247,5.10152879327731,4.0));
    DirectedWeightedGraph graph = new DirectedWeightedGraphlmpl();

    @Test
    public void TestAddVertex(){
        graph.addNode(vertex);
        graph.addNode(vertex1);
        Assertions.assertEquals(graph.getNode(0),vertex);
        Assertions.assertEquals(graph.getNode(1),vertex1);
    }

    @Test
    public void TestAddEdge(){
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.connect(0,1,1.3118716362419698);
        EdgeData edge = new EdgeDataImpl(0,1,1.3118716362419698);
        Assertions.assertEquals( graph.getEdge(0,1),edge);
    }

    @Test
    public void TestRemoveNode(){
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.connect(1,0,1.3118716362419698);
        EdgeData newEdge1 = new EdgeDataImpl(2,1,1.3118716362419698);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.connect(1,3,3.3118716362419698);
        graph.connect(0,2,0.1356);
        graph.removeNode(vertex3.getKey());
        Assertions.assertEquals(graph.edgeSize(),2);
        Assertions.assertEquals(graph.getEdge(1,0),new EdgeDataImpl(1,0,1.3118716362419698));
        Assertions.assertEquals(graph.getEdge(0,2),new EdgeDataImpl(0,2,0.1356));


    }

    @Test
    public void TestgetMC(){
        graph.addNode(vertex);
        graph.addNode(vertex1);
        graph.connect(1,0,1.3118716362419698);
        EdgeData newEdge1 = new EdgeDataImpl(2,1,1.3118716362419698);
        graph.addNode(vertex2);
        graph.addNode(vertex3);
        graph.connect(1,3,3.3118716362419698);
        graph.connect(0,2,0.1356);
        graph.removeNode(vertex3.getKey());
        Assertions.assertEquals(graph.getMC(),4);

    }


}
