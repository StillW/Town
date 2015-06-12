package java.Task02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Still on 11.06.2015.
 */
public class Graph {

    private double[][] edges;

    public Graph(int vertices) {

        edges = new double[vertices][vertices];
    }

    public void addEdge(int i, int j) {

        edges[i][j] = 1;
    }

    public void addUndirectedEdge(int i, int j) {

        edges[i][j] = 1;
        edges[j][i] = 1;
    }

    public double getEdge(int i, int j) {

        return edges[i][j];
    }

    public boolean hasEdge(int i, int j) {

        return edges[i][j] != 0.0;
    }

    public void setEdge(int i, int j, double weight) {

        edges[i][j] = weight;
    }

    public void setUndirectedEdge(int i, int j, double weight) {

        edges[i][j] = weight;
        edges[j][i] = weight;
    }

    public int size() {
        return edges.length;
    }

    public List<Integer> neighbors(int i) {

        List<Integer> result = new ArrayList<Integer>();
        for (int j = 0; j < size(); j++) {
            if (hasEdge(i, j)) {

                result.add(j);
            }
        }
        return result;
    }

    public double getCost(int i, int j) {

        if (i == j) {
            return 0.0;
        }
        if (edges[i][j] == 0.0) {
            return Double.POSITIVE_INFINITY;
        }

        return edges[i][j];
    }

    int getCheapestRoute(double[] distances, boolean[] visited) {

        int best = -1;
        for (int i = 0; i < size(); i++) {

            if (!visited[i]
                    && ((best < 0) || (distances[i] < distances[best]))) {

                best = i;
            }
        }
        return best;
    }

    public double[] distancesFrom(int source) {

        double[] result = new double[size()];

        Arrays.fill(result, Double.POSITIVE_INFINITY);

        result[source] = 0;

        boolean[] visited = new boolean[size()];
        for (int i = 0; i < size(); i++) {

            int vertex = getCheapestRoute(result, visited);
            visited[vertex] = true;

            for (int j = 0; j < size(); j++) {
                result[j] = Math.min(result[j], result[vertex] + getCost(vertex, j));
            }
        }
        return result;
    }
 /*test Graph*/

  /*
    public static void main(String args[]){

        Graph g = new Graph(5);

        g.setEdge(1,2,1);
        g.setEdge(1,3,3);

        g.setEdge(2,1,1);
        g.setEdge(2,3,1);
        g.setEdge(2,4,4);

        g.setEdge(3,1,3);
        g.setEdge(3,2,1);
        g.setEdge(3,4,1);

        g.setEdge(4,2,4);
        g.setEdge(4,3,1);

       double [] distancesFrom1 = g.distancesFrom(1);
       double [] distancesFrom2 = g.distancesFrom(2);

       System.out.println((int)distancesFrom1[4]);
       System.out.println((int)distancesFrom2[4]);

    }*/
}

