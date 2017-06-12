import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static ArrayList<Group> fillArray() throws IOException {
        ArrayList<Group> list = new ArrayList<Group>();
        Scanner in = new Scanner(new FileReader("result.txt"));
        while (in.hasNext()) {
            String next = in.next();
            String[] groupInfo = next.split("//");
            list.add(new Group(groupInfo[0], Double.parseDouble(groupInfo[1]), Double.parseDouble(groupInfo[2])));
        }
        in.close();
        return list;
    }

    public static void main(String[] args) {

        DataParser dataParser = new DataParser();
        dataParser.createDatabase();
        try {
            dataParser.getAverageData(args);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<Group> genre = fillArray();

            Song song = new Song(args[5], Double.parseDouble(args[6]), Double.parseDouble(args[7]));
            int max = 100;
            int iteration = 1;
            double probabilitySum;
            double rand;

            int hive = 10;

            List<Double> probabilities = new ArrayList<Double>();
            for (int i = 0; i < hive / 2; i++) {
                probabilities.add(0.0);
            }

            List<Flower> flowers = new ArrayList<Flower>();
            List<Bee> employed = new ArrayList<Bee>();
            List<Bee> onlookers = new ArrayList<Bee>();

            for (int i = 0; i < hive / 2; i++) {
                flowers.add(new Flower(genre.get(i).getName(), genre.get(i).getBeat(), genre.get(i).getOnset(), song));
                employed.add(new Bee(0, flowers.get(i)));
                onlookers.add(new Bee(1, null));
            }

            while (iteration <= max) {
                probabilitySum = 0.0;
                for (int i = 0; i < hive / 2; i++) {
                    flowers.get(i).setVisited(0);
                }

                //employed phase
                for (int i = 0; i < hive / 2; i++) {
                    employed.get(i).visit();
                    employed.get(i).getFlower().setVisited(employed.get(i).getFlower().getVisited() + 1);
                    probabilitySum += employed.get(i).getFlower().calculateValue(iteration);
                }

                //dance phase
                probabilities.set(0, flowers.get(0).getValue() / probabilitySum);
                probabilities.set(1, (flowers.get(1).getValue() / probabilitySum) + flowers.get(0).getValue());
                probabilities.set(2, (flowers.get(2).getValue() / probabilitySum) + flowers.get(1).getValue());
                probabilities.set(3, (flowers.get(3).getValue() / probabilitySum) + flowers.get(2).getValue());
                probabilities.set(4, (flowers.get(4).getValue() / probabilitySum) + flowers.get(3).getValue());

                //onlookers phase
                for (int i = 0; i < hive / 2; i++) {
                    rand = Math.random();
                    if (rand <= probabilities.get(0) && flowers.get(0).getExploited()) {
                        onlookers.get(i).setFlower(flowers.get(0));
                    } else if (rand <= probabilities.get(1) && flowers.get(1).getExploited()) {
                        onlookers.get(i).setFlower(flowers.get(1));
                    } else if (rand <= probabilities.get(2) && flowers.get(2).getExploited()) {
                        onlookers.get(i).setFlower(flowers.get(2));
                    } else if (rand <= probabilities.get(3) && flowers.get(3).getExploited()) {
                        onlookers.get(i).setFlower(flowers.get(3));
                    } else if (rand <= probabilities.get(4) && flowers.get(4).getExploited()) {
                        onlookers.get(i).setFlower(flowers.get(4));
                    }
                    onlookers.get(i).visit();
                    onlookers.get(i).getFlower().setVisited(onlookers.get(i).getFlower().getVisited() + 1);
                    onlookers.get(i).getFlower().setLastVisited(iteration);
                    onlookers.get(i).getFlower().calculateValue(iteration);
                }
                //System.out.println(iteration + " " + onlookers.get(0).getFlower().getType() + " " + onlookers.get(1).getFlower().getType() + " " + onlookers.get(2).getFlower().getType() + " " + onlookers.get(3).getFlower().getType() + " " + onlookers.get(4).getFlower().getType());

                for (int i = 0; i < hive / 2; i++) {
                    if (iteration - flowers.get(i).getLastVisited() == 5) {
                        flowers.get(i).setExploited(false);
                        flowers.get(i).setRichness(0);
                        //System.out.println("dieded " + iteration + " " + flowers.get(i).getType());
                    }
                }

                for (Bee employee : employed) {
                    while (employee.getFlower().getExploited() == false) {
                        Random r = new Random();
                        int x = r.nextInt(5);
                        employee.setFlower(flowers.get(x));
                    }
                }

                for (int i = 0; i < hive / 2; i++) {
                    //System.out.print(flowers.get(i).getVisited() + " ");
                }

                iteration++;
            }

            for (int i = 0; i < hive / 2; i++) {
                System.out.println(flowers.get(i).getVisited() + " " + flowers.get(i).getType());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
