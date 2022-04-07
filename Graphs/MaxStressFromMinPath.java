
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxStressFromMinPath {

    static int ret = Integer.MAX_VALUE;
    static int stressVal = Integer.MIN_VALUE;

    // 1. Find all the paths from source to destination
    // 2. Find max weight in each path
    // 3. Get Min of all these max weights found in step 2

    // There is a weighted Undirected Graph with N nodes and M edges.
    // The stress level of the path between 2 nodes is defined as the weight of the
    // edge with the maximum value in the path.
    // Given Source node and Destination node. find the min stress level.
    // If no such path exists, return -1.

    public static void main(String[] args) {
        List<Integer> graphFrom = new ArrayList<Integer>();
        List<Integer> graphTo = new ArrayList<Integer>();
        List<Integer> graphWeight = new ArrayList<Integer>();

        // graphFrom.addAll(Arrays.asList(1, 1, 2, 4));
        // graphTo.addAll(Arrays.asList(2, 4, 3, 3));
        // graphWeight.addAll(Arrays.asList(100, 10, 200, 20));
        graphFrom.addAll(Arrays.asList(1, 2, 1, 4, 1, 5));
        graphTo.addAll(Arrays.asList(2, 3, 4, 3, 5, 3));
        graphWeight.addAll(Arrays.asList(10, 5, 3, 2, 4, 6));
        System.out.println(buildGraphAndReturnStress(5, graphFrom, graphTo, graphWeight, 1, 3));

    }

    public static int buildGraphAndReturnStress(int graphNodes, List<Integer> graphFrom, List<Integer> graphTo,
            List<Integer> graphWeight, int source, int destination) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();
        for (int i = 0; i <= 5; i++)
            adj.add(new ArrayList<int[]>());

        for (int i = 0; i < graphFrom.size(); i++)
            adj.get(graphFrom.get(i)).add(new int[] { graphTo.get(i), graphWeight.get(i) });

        allPathsSourceTarget(adj, source, destination);
        return ret;
    }

    public static List<List<Integer>> allPathsSourceTarget(ArrayList<ArrayList<int[]>> adj, int source,
            int destination) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(source);
        dfsSearch(adj, source, res, path, destination);
        return res;
    }

    public static void dfsSearch(ArrayList<ArrayList<int[]>> graph, int node, List<List<Integer>> res,
            List<Integer> path, int destination) {
        if (node == destination) {
            // ret stores the Minmum of all the maximum weights in all the paths.
            ret = Math.min(ret, stressVal);
            // assign stress value to -Inf again to explore the new paths
            stressVal = Integer.MIN_VALUE;
            System.out.println("Path: " + path.toString());
            res.add(new ArrayList<>(path));
            return;
        }
        for (int[] adj : graph.get(node)) {
            path.add(adj[0]);
            // StressVal stores the maximum weight of the current path from source to destination
            stressVal = Math.max(stressVal, adj[1]);
            dfsSearch(graph, adj[0], res, path, destination);
            path.remove(path.size() - 1);
        }
    }
}
