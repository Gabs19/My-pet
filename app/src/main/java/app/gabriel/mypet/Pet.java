package app.gabriel.mypet;

public class Pet {


    private String name;
    private int age;
    private float weight;
    private int Hapyness;
    private int id;
//    private boolean live;

    public Pet () {
        this.weight = 50;
        this.age = 1;
        this.Hapyness = 25;
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

    public float getWeight () {
        return weight;
    }

    public void setWeight (float weight) {
        this.weight = weight;
    }

    public int getHapyness () {
        return Hapyness;
    }

    public void setHapyness (int hapyness) {
        this.Hapyness = hapyness;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    //
//    public boolean isLive () {
//        return live;
//    }
//
//    public void setLive (boolean live) {
//        this.live = live;
//    }
}
