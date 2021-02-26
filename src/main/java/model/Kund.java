package model;

import java.util.ArrayList;

public class Kund {
    private int id;
    private String förnamn;
    private String efternamn;
    private String ort;
    private String lösenord;
    private ArrayList<Integer> beställningar = new ArrayList<Integer>();
    //private ArrayList<Beställning> beställningar2 = new ArrayList<Beställning>();


    public Kund() {

    }

    public Kund(int id, String förnamn, String efternamn, String lösenord, String ort) {
        this.id = id;
        this.förnamn = förnamn;
        this.efternamn = efternamn;
        this.lösenord = lösenord;
        this.ort = ort;
    }

    public void addOrder(int bestid) {

        beställningar.add(bestid);
    }

    public ArrayList<Integer> getBeställningar() {
        return beställningar;
    }

    public void printAllOrders() {
        for(int i = 0; i < beställningar.size(); i++) {
            System.out.println("bestid " + beställningar.get(i));

        }
    }

    public boolean equals(Kund k) {
        if (k.getId() == this.getId()) {
            return true;
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFörnamn() {
        return förnamn;
    }

    public void setFörnamn(String förnamn) {
        this.förnamn = förnamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPassword() {
        return lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
    }

    @Override
    public String toString() {
        return "Förnamn: " + förnamn + " Efternamn: " + efternamn + " Ort: " + ort + " Lösenord: " + lösenord;
    }
}
