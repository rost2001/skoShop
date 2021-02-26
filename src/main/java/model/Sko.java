package model;

public class Sko {
    private int id;
    private String märke;
    private String färg;
    private int storlek;
    private String kategori;
    private double pris;
    private int antal;

    public Sko() {
    }

    public Sko(int id, String märke, String färg, int storlek, String kategori, double pris, int antal) {
        this.id = id;
        this.pris = pris;
        this.färg = färg;
        this.märke = märke;
        this.kategori = kategori;
        this.storlek = storlek;
        this.antal = antal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getFärg() {
        return färg;
    }

    public void setFärg(String färg) {
        this.färg = färg;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antalILager) {
        this.antal = antal;
    }


    @Override
    public String toString() {
        return "Märke: " + this.märke + " Färg: " + this.färg + " Storlek: " + this.storlek +
                " Kategori: " + this.kategori + " Pris: " + this.pris + " Antal i lager: " + this.antal;
    }



}
