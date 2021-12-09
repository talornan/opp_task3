package Ex2.src;


import Ex2.src.api.DirectedWeightedGraphlmpl;
import Ex2.src.api.EdgeData;
import Ex2.src.api.NodeData;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs
 */
public class JGraphXAdapterGraphDraw
        extends
        JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(700, 700);

    private JGraphXAdapter<Integer, DefaultEdge> jgxAdapter;

    private DirectedWeightedGraphlmpl graph;

    public JGraphXAdapterGraphDraw(DirectedWeightedGraphlmpl graph){
        this.graph = graph;
    }


    public static void showGraph(DirectedWeightedGraphlmpl graph){
        JGraphXAdapterGraphDraw applet = new JGraphXAdapterGraphDraw(graph);
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void init()
    {
        // create a JGraphT graph
        ListenableGraph<Integer, DefaultEdge> g =
                new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);


        Iterator<NodeData> nodes = graph.nodeIter();
        while(nodes.hasNext()){
            g.addVertex(nodes.next().getKey());

        }
        Iterator<EdgeData> edgesGraph = graph.edgeIter();
        while(edgesGraph.hasNext()){
            EdgeData edge = edgesGraph.next();
            g.addEdge(edge.getSrc(), (edge.getDest()));

        }

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 150;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
    }
}