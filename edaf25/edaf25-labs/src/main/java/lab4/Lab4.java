package lab4;

import java.util.*;

/**
 * Helper class for the priority queue in Dijkstras algorithm.
 */
class PQElement {
    int node;
    int distance;

    public PQElement(int node, int dist) {
        this.node = node;
        this.distance = dist;
    }
}

public class Lab4 {
    /**
     * Computes the shortest distance between start and end in the graph g using Dijkstra's
     * algorithm.
     * This version handles only graphs with integer edge distances.
     *
     * @param g     a graph with distance information attached to the edges
     * @param start start vertex
     * @param end   end vertex
     * @return shortest distance between start and end
     */
    public static int distance(DistanceGraph g, int start, int end) {
        // En Comparator skapas för att hålla listan med bågar sorterad:
        Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        // TODO(D2): slutför Dijkstras algoritm för att hitta kortaste avstånd start->end.
        PriorityQueue<PQElement> pq = new PriorityQueue<>(cmp);
        pq.add(new PQElement(start, 0));
        visited.add(start);
        map.put(start, 0);
        while (!pq.isEmpty()) {
            PQElement x = pq.poll();
            int curr = x.node;
            int dist = x.distance;
            if (curr == end) {
                return x.distance;
            } else {
                for (Edge e : g.edges(curr)) {
                    int w = e.destination;
                    int newDist = dist + e.distance;
                    if (!visited.contains(w)) {
                        visited.add(w);
                        pq.add(new PQElement(w, newDist));
                    } else {
                        int wDist = map.getOrDefault(w, Integer.MAX_VALUE);
                        if (newDist < wDist) {
                            pq.add(new PQElement(w, newDist));
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Finds a shortest path between start and end in a graph g Dijkstra's
     * algorithm.
     * The graph can have any distance unit.
     *
     * @param g     a graph with distance information attached to the edges
     * @param start start vertex
     * @param end   end vertex
     * @return a list containing the nodes on the shortest path from start to end
     */
    public static List<Integer> shortestPath(DistanceGraph g, int start, int end) {
        if (start == end) {
            return new LinkedList<Integer>();
        }
        Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
        PriorityQueue<PQElement> pq = new PriorityQueue<>(cmp);
        pq.add(new PQElement(start, 0));

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : g.vertexSet()) {
            map.put(i, Integer.MAX_VALUE);
        }
        map.put(start, 0);

        Map<Integer, Integer> prev = new HashMap<>();

        while (!pq.isEmpty()) {
            PQElement x = pq.poll();
            if (x.node == end) {
                List<Integer> path = new LinkedList<>();
                int current = end;
                while (current != start) {
                    path.add(0, current);
                    current = prev.get(current);
                    if (current == start) {
                        path.add(0, current);
                        break;
                    }
                }
                System.out.println(path);
                return path;
            } else {
                for (Edge e : g.edges(x.node)) {
                    int w = e.destination;
                    int newDist = x.distance + e.distance;
                    int wDist = map.get(w);
                    if (!map.containsKey(w) || newDist < wDist) {
                        map.put(w, newDist);
                        pq.add(new PQElement(w, newDist));
                        prev.put(w, x.node);

                    }
                }
            }
        }
        return new LinkedList<Integer>();
    }
}
