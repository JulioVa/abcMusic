/**
 * Created by Patryk on 28.05.2017.
 */
public class Song {

    private String title;
    private double beat;
    private double onset;

    public Song (String title, double beat, double onset){
        this.title = title;
        this.beat = beat;
        this.onset = onset;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBeat() {
        return beat;
    }

    public void setBeat(double beat) {
        this.beat = beat;
    }

    public double getOnset() {
        return onset;
    }

    public void setOnset(double onset) {
        this.onset = onset;
    }
}
