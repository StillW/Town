package Citys;

/**
 * Created by Still on 09.06.2015.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Graph {

    private double [][]edges;
    /*el argumento es el n?mero de v?rtices en este grafo*/
    public Graph(int vertices){

        edges = new double [vertices][vertices];
    }

    /*a?ade una arista de peso 1 a partir de i hasta j*/
    public void addEdge(int i, int j){

        edges[i][j]=1;
    }

    /*a?ade aristas de peso 1 de i hasta j y de j hasta i*/
    public void addUndirectedEdge (int i, int j){

        edges[i][j]=1;
        edges[j][i]=1;
    }

    /*retorna el costo de la arista de i y j*/
    public double getEdge(int i, int j){

        return edges[i][j];
    }

    /*retorna true si hay una arista entre i y j*/
    public boolean hasEdge (int i, int j){

        return edges[i][j] !=0.0;
    }

    /*fija el peso de la arista entre i y j*/
    public void setEdge (int i, int j, double weight){

        edges [i][j] = weight;
    }

    /*fija el peso de la arista entre i y j y entre j e i*/
    public void setUndirectedEdge (int i, int j, double weight){

        edges[i][j] = weight;
        edges[j][i] = weight;
    }

    /*retorna el n?mero de v?rtices en este grafo*/

    public int size() {
        return edges.length;
    }

    /*retorna una lista de los vecinos del v?rtice i*/

    public List <Integer> neighbors (int i){

        List <Integer> result = new ArrayList<Integer>();
        for (int j=0; j<size();j++){
            if (hasEdge(i,j)){

                result.add(j);
            }
        }
        return result;
    }

    /*retorna 0 si i y j son id?nticos, retorna infinito si no hay arista entre ellos o si
     * el peso entre las aristas si hay uno*/


    public double getCost(int i , int j){

        if (i==j){
            return 0.0;
        }
        if (edges[i][j]==0.0){
            return Double.POSITIVE_INFINITY;
        }

        return edges[i][j];
    }

    /*dijkstra, retorna el ?ndice del elemento m?s peque?o de distances, ignorando
     * aquellos en visited*/

    protected int cheapest (double [] distances, boolean [] visited){

        int best =-1;
        for (int i=0; i<size(); i++){

            if (!visited[i]
                    && ((best < 0) || (distances[i] < distances[best]))) {

                best =i;
            }
        }
        return best;

    }

    public double [] distancesFrom (int source){

        double [] result = new double[size()];

        Arrays.fill(result, Double.POSITIVE_INFINITY);

        result [source]=0;

        boolean []visited = new boolean [size()];
        for (int i =0; i<size();i++){

            int vertex = cheapest (result,visited);
            visited [vertex]=true;

            for (int j =0; j<size();j++){
                result [j] = Math.min(result[j], result[vertex]+getCost(vertex,j));
            }
        }
        return result;


    }



    /*test Graph*/
    /*public static void main(String args[]){

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


public class Main {

    public static void main(String[] args) throws Exception {


        BufferedReader r = new BufferedReader (new FileReader(new File("D:\\TSHPATHInput.txt")));
        //BufferedReader r1 = new BufferedReader (new InputStreamReader(System.in));

        String line = r.readLine();

        // System.out.println(line); //Linea de prueba

        int s = Integer.parseInt(line);

        for (int testIndex=0; testIndex<s; testIndex++){

            String [] citiesIds = new String[10000];


            line = r.readLine();

            //System.out.println(line); //Linea de prueba

            int n = Integer.parseInt(line);

            int graphSize = n +1; // por el problema de indexaci?n desde 0 en el arreglo
            Graph g = new Graph (graphSize);

            for (int cityIndex=0; cityIndex<n;cityIndex++){


                line = r.readLine();

                //      System.out.println(line); //Linea de prueba

                String NAME = line;


                int auxCityIndex = cityIndex +1; // para mantener la consistencia en la indexaci?n

                citiesIds[auxCityIndex] = NAME;

                line = r.readLine();

                //    System.out.println(line); //Linea de prueba

                int p = Integer.parseInt(line);


                for (int neighborIndex=0;neighborIndex<p;neighborIndex++){

                    line = r.readLine();

                    //      System.out.println(line); //Linea de prueba

                    String [] brokenLine = line.split(" ");

                    int cityToConnect = Integer.parseInt(brokenLine[0]);
                    int weightOfConnection = Integer.parseInt(brokenLine[1]);

                    g.setEdge(auxCityIndex,cityToConnect, weightOfConnection);

                }



            }

            line = r.readLine();

            //System.out.println(line); //Linea de prueba

            int routesToFind = Integer.parseInt(line);



            for (int routesIndex=0; routesIndex<routesToFind; routesIndex++){

                line = r.readLine();

                //  System.out.println(line); //Linea de prueba

                String [] cityNames = line.split(" ");

                String source = cityNames[0];
                String destination = cityNames[1];



                int sourceIndex = Arrays.binarySearch(citiesIds,source);

                int destinationIndex= Arrays.binarySearch(citiesIds, destination);

                double [] distancesFromSource = g.distancesFrom(sourceIndex);

                int destinationDistance = (int)distancesFromSource[destinationIndex];

                System.out.println(destinationDistance);


            }


        }

    }

}