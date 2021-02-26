
package view;
/*
import databas.Repository;
import model.Kund;

import java.util.List;
import java.util.Scanner;

public class SkokopView {
    Scanner sc = new Scanner(System.in);
    Repository rs = new Repository();
    List<Kund> kunder = rs.getAllCustomer();
    Kund kund;

    public void meny() {
        System.out.println("1. Show all available products\n2.Add product\n3.Show my cart\n4.New order\n5.Exit");
        while (true) {
            System.out.println("Choose an option: ");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    rs.getAllShoes();
                    break;
                case "2":
                    System.out.println("Choose brand: ");
                    String brand = sc.nextLine().trim();
                    System.out.println("Choose type: ");
                    String category = sc.nextLine().trim();
                    System.out.println("Choose size: ");
                    String size = sc.nextLine().trim();
                    rs.addShoesToCart(brand, category, size);
                    break;
                case "3":
                    rs.showMyCart();
                case "5":
                    System.out.println("Thank you!");
                    System.exit(0);
            }

        }

    }


    public void run() {
        // System.out.println(rs.addToCart(2,2,5));

        System.out.println("--Skor Databas--");

        System.out.println("Förnamn: ");
        String name = sc.nextLine().trim();
        System.out.println("Lösenord: ");
        String pass = sc.nextLine().trim();
        rs.checkCustomerStatus(name, pass, kunder);

    }

    public static void main(String[] args) {
        SkokopView view = new SkokopView();
        view.run();

        view.meny();


    }
}
*/