package Ex2.src;

import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.DirectedWeightedGraphAlgorithms;
import Ex2.src.api.DirectedWeightedGraphAlgorithmslmpl;
import Ex2.src.api.DirectedWeightedGraphlmpl;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphlmpl ans = new DirectedWeightedGraphlmpl();
        DirectedWeightedGraphAlgorithmslmpl alg = new DirectedWeightedGraphAlgorithmslmpl(ans);
        alg.load(json_file);
        return alg.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphlmpl ans = new DirectedWeightedGraphlmpl();
        DirectedWeightedGraphAlgorithmslmpl alg = new DirectedWeightedGraphAlgorithmslmpl(ans);
        alg.load(json_file);
        return alg;
        }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        Graphics2D graph;
        // creating object of JFrame(Window popup)
        JFrame window = new JFrame();

        // setting closing operation
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting size of the pop window
        window.setBounds(50, 50, 10000, 10000);

        // setting canvas for draw
        DirectedWeightedGraphlmpl directedWeightedGraphlmpl = (DirectedWeightedGraphlmpl)getGrapg(json_file);
        window.getContentPane().add(new ShowGraph(directedWeightedGraphlmpl));

        // set visibility
        window.setVisible(true);
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\talor\\IdeaProjects\\opp_task2\\src\\Ex2\\data\\G1.json";

        runGUI(path);
    }
}