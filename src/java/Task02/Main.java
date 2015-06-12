package java.Task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by Still on 11.06.2015.
 * We use Dijkstra Algoritm
 */
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader r = new BufferedReader(new FileReader(new File("D:\\TSHPATHInput.txt"))); //file with input data
        //BufferedReader r1 = new BufferedReader (new InputStreamReader(System.in));

        String line = r.readLine();

        int s = Integer.parseInt(line);

        for (int testIndex = 0; testIndex < s; testIndex++) {

            String[] citiesIds = new String[100];

            line = r.readLine();

            int n = Integer.parseInt(line);

            int graphSize = n + 1;
            Graph g = new Graph(graphSize);

            for (int cityIndex = 0; cityIndex < n; cityIndex++) {

                line = r.readLine();

                String NAME = line;

                int auxCityIndex = cityIndex + 1;

                citiesIds[auxCityIndex] = NAME;

                line = r.readLine();

                int p = Integer.parseInt(line);

                for (int neighborIndex = 0; neighborIndex < p; neighborIndex++) {

                    line = r.readLine();

                    String[] brokenLine = line.split(" ");

                    int cityToConnect = Integer.parseInt(brokenLine[0]);
                    int weightOfConnection = Integer.parseInt(brokenLine[1]);

                    g.setEdge(auxCityIndex, cityToConnect, weightOfConnection);

                }

            }

            line = r.readLine();

            final int routesToFind = Integer.parseInt(line);

            for (int routesIndex = 0; routesIndex < routesToFind; routesIndex++) {

                line = r.readLine();

                String[] cityNames = line.split(" ");

                String source = cityNames[0];
                String destination = cityNames[1];

                int sourceIndex = Arrays.asList(citiesIds).indexOf(source);

                int destinationIndex = Arrays.asList(citiesIds).indexOf(destination);

                double[] distancesFromSource = g.distancesFrom(sourceIndex);

                int destinationDistance = (int) distancesFromSource[destinationIndex];

                System.out.println(destinationDistance);

            }

        }

    }

}


