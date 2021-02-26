package model;

import java.util.ArrayList;

public class Beställning {
    private int id;
    private String datum; // kan ändras till date
    private int total;
    private ArrayList<Integer> skor = new ArrayList<Integer>();
    private Sko s; // ha som en temp

    public Beställning(int id) {
        this.id = id;

    }

    public Beställning(int id, int total, String datum) {
        this.id = id;
        this.total = total;
        this.datum = datum;
    }

    public void addSko(int skoid) {
        skor.add(skoid);
    }

    public ArrayList<Integer> getSkor() {
        return skor;
    }

    public void printAllShoes() {
        for (int i = 0; i < skor.size(); i++) {
            System.out.println("skoid " + skor.get(i));
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDatum() {
        return datum;
    }

    /*
    public void setDatum(Date datum) {
        this.datum = datum;
    }
     */
}
