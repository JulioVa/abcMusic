/**
 * Created by Patryk on 28.05.2017.
 */
public class Flower {

    private String type;
    private boolean exploited;
    private double distance;  //abs(groupbps-songbps)
    private double quality;   //abs(grouponset-songonset)
    private int richness;     //how many bees visited
    private double value;
    private int lastVisited;  //on which iteration was the latest visit

    public Flower (String type, double distance, double quality, Song song) {
        this.exploited = true;
        this.type = type;
        this.distance = calculate(distance, song.getBeat());
        this.quality = calculate(quality, song.getOnset());
        this.richness = 0;
        calculateValue(1);
        this.lastVisited = 0;
    }

    private double calculate (double flowerVariable, double song){
        return Math.abs(flowerVariable - song);
    }

    public void calculateValue(int iteration){
        value = distance*quality*(richness/iteration);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExploited() {
        return exploited;
    }

    public void setExploited(boolean exploited) {
        this.exploited = exploited;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public int getRichness() {
        return richness;
    }

    public void setRichness(int richness) {
        this.richness = richness;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(int lastVisited) {
        this.lastVisited = lastVisited;
    }
}
