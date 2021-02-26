package model;

import java.util.ArrayList;

public class Kategori {
    //int id;
    String namn;
    ArrayList<Sko> skor = new ArrayList<Sko>();

    public Kategori(String name) {
        //this.id = id;
        this.namn = name;
    }

    public void addSko(Sko s) {
        skor.add(s);
    }

    public void printAllShoes() {
        for(int i = 0; i < skor.size(); i++) {
            System.out.println(skor.get(i).toString());
        }
    }

    //public int getId() {
       // return id;
    //}

    public void setId(int id) {
        id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        namn = namn;
    }
}
