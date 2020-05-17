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
        int count = g.vertexCount();            // no of vertices
        int paths = 0;
        // Graph variables
        int[][] flow = new int[count][count];   // flow[x][y] returns flow in edge x-to-y
        int[][] cap = new int[count][count];    // cap[x][y] returns remaining capacity in edge x-to-y
        for (int j = 0; j < count; j++){
            for (int i= 0; i<count; i++){
                cap[i][j] = g.getCapacity(i,j);
            }
        }

        while(true){    // dirty...
            paths++;
            // Queue variables
            int current;                                // current node
            boolean[] visited = new boolean[count];     // visited "set"
            int[] previous = new int[count];            // previous[x] returns the previous node to x, in the path
            boolean atSink = false;
            Queue<Integer> q = new PriorityQueue<>();   // queue for BFS
            q.add(source);                              // add the first node to queue

            Arrays.fill(visited,false);             // defaults all visited to false
            visited[source] = true;                     // but source is always visited

            // Find shortest path
            while(!q.isEmpty()){
                if(q.peek() == sink){
                    atSink = true;
                    break;
                }

                current = q.poll();
                for (int i = 0; i < count; i++) {
                    if(!visited[i] && cap[current][i] > flow[current][i]){
                        visited[i] = true;
                        q.add(i);
                        previous[i] = current;
                    }
                }
            }
            if(!atSink) break;  // if the queue is empty and we haven't reached the sink, we have augmented all paths!

            // Finds the bottleneck in the path
            int bottleneck = cap[previous[sink]][sink] - flow[previous[sink]][sink];
            for (int i = sink; i != source; i = previous[i]){
                bottleneck = Math.min(bottleneck, (cap[previous[i]][i] - flow[previous[i]][i]));
            }

            // Augment path
            for (int i = sink; i != source; i = previous[i]) {
                flow[previous[i]][i] += bottleneck;
                flow[i][previous[i]] -= bottleneck;
            }
        }

        // Calculate flow
        int maxFlow = 0;
        for (int i = 0; i < count; i++) {
            maxFlow += flow[source][i];
        }

        return maxFlow;
    }

    /**
     * Read a flowgraph from a file.
     */
    public static FlowGraph loadFlowGraph(Path path) throws IOException {
        // TODO(D3): läs in ett flödesnätverk från fil.
        // Filen börjar med ett heltal som anger antalet noder,
        // sedan följer ett tal m som är antalet bågar.
        // Resten av filen består av m rader där varje rad anger en båge i
        // formatet u v c som beskriver en båge från en nod u till v med kapacitet c.

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
            if(cap<0) cap = Integer.MAX_VALUE;
            edges[i] = new FlowEdge(src, dest, cap);
        }

        return new FlowGraph(vertexCount, edges);
    }
}
