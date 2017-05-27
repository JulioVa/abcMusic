import java.io.*;
import java.util.ArrayList;

public class Main {

    private final static String dbName = "C:\\Users\\damia_000\\Desktop\\Studia\\BO\\abcMusic\\src\\main\\resources\\data.txt";
    private final static String dbWriteName = "C:\\Users\\damia_000\\Desktop\\Studia\\BO\\abcMusic\\src\\main\\resources\\db.txt";
    public static void main(String[] args) {
        createDatabase();
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
