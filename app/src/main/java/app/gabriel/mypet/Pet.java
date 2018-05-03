package app.gabriel.mypet;

public class Pet {


    private String name;
    private int age;
    private String type;
    private float weight;
    private int Hapyness;
    private int id;
    private int hungry;
//    private boolean live;

    public Pet () {
        this.age = 1;
        this.hungry = 15;
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

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public int getHungry () {
        return hungry;
    }

    public void setHungry (int fome) {
        this.hungry = fome;
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
