package model;

public class Märke {
    int id;
    String namn;

    public Märke(int id, String name) {
        this.id = id;
        this.namn = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    @Override
    public String toString() {
        return namn;
    }
}
