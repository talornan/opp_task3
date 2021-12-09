package Ex2.src;

import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.DirectedWeightedGraphAlgorithms;
import Ex2.src.api.DirectedWeightedGraphAlgorithmslmpl;
import Ex2.src.api.DirectedWeightedGraphlmpl;

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
        DirectedWeightedGraphlmpl directedWeightedGraphlmpl = (DirectedWeightedGraphlmpl)getGrapg(json_file);
        JGraphXAdapterGraphDraw.showGraph(directedWeightedGraphlmpl);

    }

    public static void main(String[] args) {
        String path = args[0];
        runGUI(path);
    }
}