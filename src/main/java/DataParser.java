import java.io.*;
import java.util.ArrayList;

public class DataParser {

    private final static String dbName = "src/main/resources/data.txt";
    private final static String dbWriteName = "src/main/resources/db.txt";

    public void createDatabase() {

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

                for (String onset : text.substring(onsetStart, onsetEnd).split(", ")) {
                    onsets.add(Float.parseFloat(onset));
                }

                onsetPS = onsets.size() / onsets.get(onsets.size() - 1);

                beatStart = text.indexOf("\"beats\":\"[") + "\"beats\":\"[".length();
                beatEnd = text.lastIndexOf("]\",\"name\"");
                beats = new ArrayList<Float>();

                for (String onset : text.substring(beatStart, beatEnd).split(", ")) {
                    beats.add(Float.parseFloat(onset));
                }

                beatPS = beats.size() / beats.get(beats.size() - 1);


                nameStart = text.indexOf("\"name\":\"") + "\"name\":\"".length();
                nameEnd = text.lastIndexOf("\"}");
                name = text.substring(nameStart, nameEnd);

                out.println(name + "\\\\" + beatPS + "\\\\" + onsetPS);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Song> createDatabaseToArray(String fileName) {

        ArrayList<Song> list = new ArrayList<Song>();

        File file = new File(fileName);
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

            while ((text = reader.readLine()) != null) {

                onsetStart = text.indexOf("\"onset\":\"[") + "\"onset\":\"[".length();
                onsetEnd = text.indexOf("]");
                onsets = new ArrayList<Float>();

                for (String onset : text.substring(onsetStart, onsetEnd).split(", ")) {
                    onsets.add(Float.parseFloat(onset));
                }

                onsetPS = onsets.size() / onsets.get(onsets.size() - 1);

                beatStart = text.indexOf("\"beats\":\"[") + "\"beats\":\"[".length();
                beatEnd = text.lastIndexOf("]\",\"name\"");
                beats = new ArrayList<Float>();

                for (String onset : text.substring(beatStart, beatEnd).split(", ")) {
                    beats.add(Float.parseFloat(onset));
                }

                beatPS = beats.size() / beats.get(beats.size() - 1);


                nameStart = text.indexOf("\"name\":\"") + "\"name\":\"".length();
                nameEnd = text.lastIndexOf("\"}");
                name = text.substring(nameStart, nameEnd);

                Song song = new Song(name, beatPS, onsetPS);
                list.add(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void getAverageData(String[] fileNames) throws IOException {

        PrintWriter writer = new PrintWriter("result.txt", "UTF-8");

        for (int i = 0; i < fileNames.length - 3; i++) {
            String fileName = fileNames[i];
            ArrayList<Song> list = createDatabaseToArray(fileName);
            Double sumBeat = 0.0;
            Double sumOnset = 0.0;
            for (Song s : list) {
                sumBeat += s.getBeat();
                sumOnset += s.getOnset();
            }
            Double resultBeat = sumBeat / list.size();
            Double resultOnset = sumOnset / list.size();
            writer.println(i + 1 + "//" + String.valueOf(resultBeat) + "//" + String.valueOf(resultOnset));
        }
        writer.close();
    }

    /*public static void main(String[] args) {
        createDatabase();
        try {
            getAverageData(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
