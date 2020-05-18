package lab5;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lab5 {
    /**
     * Computes the maximum flow for a flow network.
     *
     * @param g a graph with edge capacities and a source and sink
     * @return shortest distance between start and end
     */
    public static int maxFlow(FlowGraph g, int source, int sink) {
        int numVertex = g.vertexCount();                            // no of vertices in graph
        int flow = 0;                                               // the max flow in the graph
        int[] pred = new int[numVertex];                            // holds predecessor nodes in path
        int[][] residual = new int[numVertex][numVertex];           // residual graph


        for (int j = 0; j < numVertex; j++)                         //
            for (int i = 0; i < numVertex; i++)                     // Set residual graph capacities to original graph
                residual[i][j] = g.getCapacity(i, j);               //

        while (bfs(numVertex, source, sink, residual, pred)) {              // As long as we keep finding augment paths
            int bottleneck = residual[pred[sink]][sink];                        // Find the bottleneck in the path
            for (int i = sink; i != source; i = pred[i])                        // iterate over path
                bottleneck = Math.min(bottleneck, residual[pred[i]][i]);        // set bottleneck to smallest residual capacity

            for (int i = sink; i != source; i = pred[i]) {                      // Update residual capacities
                residual[pred[i]][i] -= bottleneck;                             // Forward edges get subtracted
                residual[i][pred[i]] += bottleneck;                             // Backwards edges get added
            }
            flow += bottleneck;                                                 // add the bottleneck capacity in this path to max flow
        }

        return flow;                                                // When no more augment paths can be found, max flow is found!
    }

    private static boolean bfs(int numVertex, int source, int sink, int[][] residual, int[] pred) {
        int current;                                        // the current node
        boolean[] visited = new boolean[numVertex];         // holds if a node is visited already or not
        Arrays.fill(visited, false);                    // default all nodes as NOT VISITED
        visited[source] = true;                             // but start node is always visited

        Queue<Integer> q = new PriorityQueue<>();           // the queue
        q.add(source);                                      // add start node to queue to get started!

        while (!q.isEmpty()) {
            if (q.peek() == sink)                                   // we've arrived at the sink, a path is found!
                return true;
            current = q.poll();                                     // get the next node
            for (int i = 0; i < numVertex; i++) {
                if (!visited[i] && residual[current][i] > 0) {      // if other edge to node has POSITIVE RESIDUAL CAPACITY
                    visited[i] = true;                              // mark node as visited
                    q.add(i);                                       // add node to queue
                    pred[i] = current;                              // set the node's predecessor to the current node
                }
            }
        }
        return false;                                       // the queue is empty and we didn't arrive at the sink :(
    }

    /**
     * Read a flowgraph from a file.
     */
    public static FlowGraph loadFlowGraph(Path path) throws IOException {
        int vertexCount;
        int edgeCount;
        FlowEdge[] edges;

        Scanner scan = new Scanner(path.toFile());

        String vCount = scan.nextLine();
        String eCount = scan.nextLine();
        vertexCount = Integer.parseInt(vCount);
        edgeCount = Integer.parseInt(eCount);
        edges = new FlowEdge[edgeCount];

        for (int i = 0; i < edgeCount; i++) {
            String edge = scan.nextLine();
            String[] edgeParts = edge.split(" ");
            int src = Integer.parseInt(edgeParts[0]);
            int dest = Integer.parseInt(edgeParts[1]);
            int cap = Integer.parseInt(edgeParts[2]);
            if (cap < 0) cap = Integer.MAX_VALUE;
            edges[i] = new FlowEdge(src, dest, cap);
        }

        return new FlowGraph(vertexCount, edges);
    }
}
