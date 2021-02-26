package model;

public class Storlek {
    private int id;
    private int nummer;

    public Storlek(int id, int nummer) {
        this.id = id;
        this.nummer = nummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }
}

