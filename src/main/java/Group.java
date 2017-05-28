/**
 * Created by Patryk on 28.05.2017.
 */
public class Group {

    private String name;
    private double beat;
    private double onset;

    public Group(String name, double beat, double onset) {
        this.name = name;
        this.beat = beat;
        this.onset = onset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
