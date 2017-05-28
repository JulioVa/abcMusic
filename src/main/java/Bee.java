/**
 * Created by Patryk on 28.05.2017.
 */
public class Bee {

    private int type; //worker or onlooker
    private Flower flower;

    public Bee(int type, Flower flower){
        this.type = type;
        this.flower = flower;
    }

    public void visit(){
        flower.setRichness(flower.getRichness() + 1);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }
}
