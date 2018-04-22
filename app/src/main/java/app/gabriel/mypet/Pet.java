package app.gabriel.mypet;

public class Pet {

    private String name;
    private int age;
    private double weight;
    private int Hapyness;
    private boolean live;

    public Pet () {
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        this.age = age;
    }

    public double getWeight () {
        return weight;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }

    public int getHapyness () {
        return Hapyness;
    }

    public void setHapyness (int hapyness) {
        Hapyness = hapyness;
    }

    public boolean isLive () {
        return live;
    }

    public void setLive (boolean live) {
        this.live = live;
    }
}
