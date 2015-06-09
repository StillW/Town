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

    private double [][]edges; //матрица из N строк и X столбцов

    public Graph(int vertices){ //вершины

        edges = new double [vertices][vertices]; //края заполняем вершинами матрица 5 на 5
    }

    public void addEdge(int i, int j){

        edges[i][j]=1;
    }

    public void addUndirectedEdge (int i, int j){

        edges[i][j]=1;
        edges[j][i]=1;
    }

    public double getEdge(int i, int j){

        return edges[i][j];
    }

    public boolean hasEdge (int i, int j){

        return edges[i][j] !=0.0;
    }

    public void setEdge (int i, int j, double weight){

        edges [i][j] = weight;
    }

    public void setUndirectedEdge (int i, int j, double weight){

        edges[i][j] = weight;
        edges[j][i] = weight;
    }

    public int size() {
        return edges.length;
    }

    public List <Integer> neighbors (int i){

        List <Integer> result = new ArrayList<Integer>();
        for (int j=0; j<size();j++){
            if (hasEdge(i,j)){

                result.add(j);
            }
        }
        return result;
    }

    public double getCost(int i , int j){

        if (i==j){
            return 0.0;
        }
        if (edges[i][j]==0.0){
            return Double.POSITIVE_INFINITY;
        }

        return edges[i][j];
    }

     int getCheapestRoute (double[] distances, boolean[] visited){ //самый короткий путь

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

            int vertex = getCheapestRoute(result, visited);
            visited [vertex]=true;

            for (int j =0; j<size();j++){
                result [j] = Math.min(result[j], result[vertex]+getCost(vertex,j));
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

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader r = new BufferedReader(new FileReader(new File("D:\\TSHPATHInput.txt")));
        //BufferedReader r1 = new BufferedReader (new InputStreamReader(System.in));

        String line = r.readLine();


        int s = Integer.parseInt(line); // парсим строку из файла

        for (int testIndex=0; testIndex<s; testIndex++){

            String [] citiesIds = new String[6]; //массив из 100 элементов айди городов

            line = r.readLine(); //читаем еще строку из файла

            int n = Integer.parseInt(line); //парсим

            int graphSize = n +1; //прибавляем еденицу к распарсиному значинию будет 5
            Graph g = new Graph (graphSize); //создаем обьект класса Вершины

            for (int cityIndex=0; cityIndex<n;cityIndex++){ //тут n = 4

                line = r.readLine(); //читаем третью строку - это название гороода

                String NAME = line; //присваемваем его в ИМЯ

                int auxCityIndex = cityIndex +1;

                citiesIds[auxCityIndex] = NAME; //загоняем Имя в массив в первом индексе будет лежать gdansk

                line = r.readLine(); //читаем опять

                int p = Integer.parseInt(line); //парсим тут количество соседних городов

                for (int neighborIndex=0;neighborIndex<p;neighborIndex++){

                    line = r.readLine();

                    String [] brokenLine = line.split(" "); //разбиваем полученную строку на индексы массива

                    int cityToConnect = Integer.parseInt(brokenLine[0]); //тут индекс соседнего города 2
                    int weightOfConnection = Integer.parseInt(brokenLine[1]); //тут стоимость до него

                    g.setEdge(auxCityIndex,cityToConnect, weightOfConnection);//тут передаем Город, ближайший к нему индекс и стоимость

                }

            }

            line = r.readLine();

            int routesToFind = Integer.parseInt(line);

            for (int routesIndex=0; routesIndex<routesToFind; routesIndex++){

                line = r.readLine();

                String [] cityNames = line.split(" ");

                String source = cityNames[0];
                String destination = cityNames[1];

                int sourceIndex = Arrays.binarySearch(citiesIds,source);

                int destinationIndex= Arrays.binarySearch(citiesIds, destination); //на этой строчке выкидывает NullPointerExceprion если в citiesIds дли массива больше 6
                                                                                    //если меньше семи то .ArrayIndexOutOfBoundsException: -4

                double [] distancesFromSource = g.distancesFrom(sourceIndex);

                int destinationDistance = (int)distancesFromSource[destinationIndex];

                System.out.println(destinationDistance);

            }

        }

    }

}
