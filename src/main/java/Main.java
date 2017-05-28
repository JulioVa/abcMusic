import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static String dbName = "C:\\Users\\damia_000\\Desktop\\Studia\\BO\\abcMusic\\src\\main\\resources\\data.txt";
    private final static String dbWriteName = "C:\\Users\\damia_000\\Desktop\\Studia\\BO\\abcMusic\\src\\main\\resources\\db.txt";
    public static void main(String[] args) {
        //createDatabase();

        Song song = new Song("xcvbn", 2.2, 2.67);
        int max = 50;
        int iteration = 1;
        double probabilitySum;
        double rand;

        int hive = 10;
        List<Group> genre = new ArrayList<Group>();
        genre.add(new Group("rock", 2.5, 2.4));
        genre.add(new Group("rap", 3.14, 1.8));
        genre.add(new Group("classical", 1.2, 4.2));
        genre.add(new Group("disco", 5.0, 5.0));
        genre.add(new Group("country", 3.65, 2.13));

        List<Double> probabilities = new ArrayList<Double>();
        for (int i = 0; i < hive/2; i++) {
            probabilities.add(0.0);
        }

        List<Flower> flowers = new ArrayList<Flower>();
        List<Bee> employed = new ArrayList<Bee>();
        List<Bee> onlookers = new ArrayList<Bee>();

        for (int i = 0; i < hive/2; i++) {
            flowers.add(new Flower(genre.get(i).getName(), genre.get(i).getBeat(), genre.get(i).getOnset(), song));
            employed.add(new Bee(0, flowers.get(i)));
            onlookers.add(new Bee(1, null));
        }

        //TODO wyrzucanie kwiatkow, szukanie kwiatka dla bezkwiatkowej pszczoly

        while (iteration <= max){
            probabilitySum = 0.0;
            for (int i = 0; i < hive/2; i++) {
                flowers.get(i).setVisited(0);
            }

            //employed phase
            for (int i = 0; i < hive/2; i++) {
                employed.get(i).visit();
                employed.get(i).getFlower().setVisited(employed.get(i).getFlower().getVisited()+1);
                employed.get(i).getFlower().setLastVisited(iteration);
                employed.get(i).getFlower().setRichness(employed.get(i).getFlower().getRichness() + 1);
                probabilitySum += employed.get(i).getFlower().calculateValue(iteration);
            }

            //dance phase
            probabilities.set(0, flowers.get(0).getValue()/probabilitySum);
            probabilities.set(1, (flowers.get(1).getValue()/probabilitySum) + flowers.get(0).getValue());
            probabilities.set(2, (flowers.get(2).getValue()/probabilitySum) + flowers.get(1).getValue());
            probabilities.set(3, (flowers.get(3).getValue()/probabilitySum) + flowers.get(2).getValue());
            probabilities.set(4, (flowers.get(4).getValue()/probabilitySum) + flowers.get(3).getValue());

            //onlookers phase
            for (int i = 0; i < hive/2; i++) {
                rand = Math.random();
                if (rand <= probabilities.get(0)) {
                    onlookers.get(i).setFlower(flowers.get(0));
                } else if (rand <= probabilities.get(1)) {
                    onlookers.get(i).setFlower(flowers.get(1));
                } else if (rand <= probabilities.get(2)) {
                    onlookers.get(i).setFlower(flowers.get(2));
                } else if (rand <= probabilities.get(3)) {
                    onlookers.get(i).setFlower(flowers.get(3));
                } else if (rand <= probabilities.get(4)) {
                    onlookers.get(i).setFlower(flowers.get(4));
                }
                onlookers.get(i).visit();
                onlookers.get(i).getFlower().setVisited(employed.get(i).getFlower().getVisited()+1);
                onlookers.get(i).getFlower().setLastVisited(iteration);
                onlookers.get(i).getFlower().setRichness(employed.get(i).getFlower().getRichness() + 1);
                onlookers.get(i).getFlower().calculateValue(iteration);

            }

            iteration++;
        }

        for (int i = 0; i < hive/2; i++) {
            System.out.println(flowers.get(i).getVisited());
        }

    }

    public static void createDatabase() {

        File file = new File(dbName);
        BufferedReader reader = null;

        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            reader = new BufferedReader(new FileReader(file));

            String text, name;
            int onsetStart, beatStart, nameStart;
            int onsetEnd, beatEnd, nameEnd;
            ArrayList<Float> onsets, beats;
            float onsetPS = 0, beatPS = 0;

            fileWriter = new FileWriter(dbWriteName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bufferedWriter);


            while ((text = reader.readLine()) != null) {

                onsetStart = text.indexOf("\"onset\":\"[") + "\"onset\":\"[".length();
                onsetEnd = text.indexOf("]");
                onsets = new ArrayList<Float>();

                for(String onset: text.substring(onsetStart,onsetEnd).split(", "))
                {
                   onsets.add(Float.parseFloat(onset));
                }

                onsetPS = onsets.size()/onsets.get(onsets.size()-1);

                beatStart = text.indexOf("\"beats\":\"[") + "\"beats\":\"[".length();
                beatEnd = text.lastIndexOf("]\",\"name\"");
                beats = new ArrayList<Float>();

                for(String onset: text.substring(beatStart,beatEnd).split(", "))
                {
                    beats.add(Float.parseFloat(onset));
                }

                beatPS = beats.size()/beats.get(beats.size()-1);


                nameStart = text.indexOf("\"name\":\"") + "\"name\":\"".length();
                nameEnd = text.lastIndexOf("\"}");
                name = text.substring(nameStart,nameEnd);

                out.println(name + "\\\\" + beatPS + "\\\\" + onsetPS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
