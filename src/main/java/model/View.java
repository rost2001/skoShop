package model;

import databas.Repository2;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    Scanner sc = new Scanner(System.in);
    Repository2 repo = new Repository2();
    //int bestid;
    //Sko aktuellSko = null;
    public static ArrayList<Sko> skor;
    public static ArrayList<Beställning> beställningar;
    private static ArrayList<Kund> kunder;
    private Kund aktuellKund = null;
    private Beställning b;
    private Sko s;
    private ArrayList<Sko> nuvarandeSkor = new ArrayList<>();


    public void login() {
        System.out.println("--Skoshop--");
        System.out.println("Förnamn: ");
        String name = sc.nextLine().trim();
        System.out.println("Lösenord: ");
        String pass = sc.nextLine().trim();
        aktuellKund = checkLogin(name, pass); // får en kund
    }

    public Kund checkLogin(String name, String pass) {

        Kund m = null;
        for (Kund k : kunder) {
            if (k.getFörnamn().equalsIgnoreCase(name) && k.getPassword().equals(pass)) {
                m = k;
                System.out.println("Välkommen " + k.getFörnamn() + " " + k.getEfternamn());
                break;
            } else {
                System.out.println("Kunden hittades inte");
                System.exit(1);
            }
        }
        return m;
    }

    public void meny()  {
        System.out.println("1.Visa alla produkter\n2.Gör ny beställning\n3.Visa mina beställnigar\n4.Slut");
        while (true) {
            System.out.println("Välj ett alternativ:");
            int input = sc.nextInt();
            switch (Integer.toString(input)) {
                case "1":
                    for (int i = 0; i < skor.size(); i++) {
                        System.out.println(i + 1 + ". " + skor.get(i).toString());
                    }
                    break;
                case "2":
                    System.out.println("Välj en sko: ");
                    int alternativ = sc.nextInt();
                    int skoAlternativ = skor.get(alternativ-1).getId();//id för sko man har valt
                    int nyasteBest = beställningar.size() +1;
                    repo.addToCart(aktuellKund.getId() , nyasteBest, skoAlternativ);
                    b = new Beställning(nyasteBest);

                    for (int i = 0; i < skor.size(); i++) {
                        if (skor.get(i).getId() == skoAlternativ) {
                            b.addSko(skor.get(i).getId());
                            nuvarandeSkor.add(skor.get(i));
                        }
                }
                    break;
                case "3":
                    printNyaSkor();
                    break;
                case "4":
                    System.out.println("Tack");
                    System.exit(0);
            }
        }
    }

    public void printNyaSkor() {
        int total_pris = 0;
        System.out.println("Mina beställningar:");
        for(Sko s : nuvarandeSkor) {
            System.out.println(s.getMärke()+" " + s.getKategori() + " " + s.getStorlek());
            total_pris += s.getPris();
        }
        System.out.println("Beställningen kostar: "+total_pris);
    }


    public void visaGamlaBeställningar(Kund k) {
        for (int i = 0; i < k.getBeställningar().size(); i++) {
            for (int j = 0; j < beställningar.size(); j++) {
                if (beställningar.get(j).getId() == k.getBeställningar().get(i)) {
                    System.out.println(beställningar.get(j).getDatum());
                    for (int m = 0; m < beställningar.get(j).getSkor().size(); m++) {
                        for (int l = 0; l < skor.size(); l++) {
                            if (beställningar.get(j).getSkor().get(m) == skor.get(l).getId()) {
                                System.out.println(skor.get(l).toString());
                            }
                        }
                    }
                }
            }
        }


    }

    public static void main(String[] args) {
        View v = new View();
        skor = v.repo.getAllProducts();
        kunder = v.repo.getAllCustomers();
        beställningar = v.repo.getAllOrders();

        v.login();
        v.meny();
    }
}
