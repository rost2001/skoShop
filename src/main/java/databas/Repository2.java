package databas;

import model.Beställning;
import model.Kategori;
import model.Kund;
import model.Sko;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Repository2 {

    private Properties p = new Properties();
    //private ArrayList<Kategori> kategorier = new ArrayList<Kategori>();


    public Repository2() {
        try {
            p.load(new FileInputStream("src/main/java/model/Settings.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sko> getAllProducts() {
        ArrayList<Sko> skor = new ArrayList<Sko>();

        String query = "   SELECT skor.id, märke.namn as märke, skor.färg, storlek.nummer as storlek, kategori.namn as kategori, skor.pris, finns_i.antal\n" +
                "from skor\n" +
                "join märke\n" +
                "on skor.märkesid = märke.id\n" +
                "join kategori\n" +
                "on skor.katid = kategori.id\n" +
                "join finns_i\n" +
                "on finns_i.skoid = skor.id\n" +
                "join storlek\n" +
                "on finns_i.storid = storlek.id;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"))) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String märke = rs.getString("märke");
                String färg = rs.getString("färg");
                int storlek = rs.getInt("storlek");
                String kategori = rs.getString("kategori");
                double pris = rs.getDouble("pris");
                int antal = rs.getInt("antal");

                Sko s = new Sko(id, märke, färg, storlek, kategori, pris, antal);
                Kategori k = new Kategori(kategori);
                k.addSko(s);
                skor.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return skor;
    }

    public ArrayList<Beställning> getAllOrders() {
        ArrayList<Beställning> beställningar = new ArrayList<Beställning>();

        String query = "select beställning.id, beställning.datum, innehåller.skoid," +
                " innehåller.antal, beställning.total\n" +
                "    from beställning\n" +
                "    join innehåller\n" +
                "    on innehåller.bestid = beställning.id;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"))) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String datum = rs.getString("datum");
                int skoid = rs.getInt("skoid");
                int antal = rs.getInt("antal");
                int total = rs.getInt("total");
                Beställning b = new Beställning(id, total, datum);

                for (int i = 0; i < antal; i++) {
                    b.addSko(skoid);
                }
                beställningar.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return beställningar;
    }

    public ArrayList<Kund> getAllCustomers() {
        ArrayList<Kund> kunder = new ArrayList<Kund>();

        String query = "select kund.id, kund.förnamn, kund.efternamn, " +
                "kund.lösenord, kund.ort, beställning.id as bestid\n" +
                "    from kund\n" +
                "    join beställning\n" +
                "    on beställning.kundid = kund.id;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"))) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String förnamn = rs.getString("förnamn");
                String efternamn = rs.getString("efternamn");
                String lösenord = rs.getString("lösenord");
                String ort = rs.getString("ort");
                int bestid = rs.getInt("bestid");

                Kund k = new Kund(id, förnamn, efternamn, lösenord, ort);

                if (kunder.isEmpty() || !(kunder.get(kunder.size() - 1).getId() == id)) {
                    kunder.add(k);
                    k.addOrder(bestid);
                } else if ((kunder.get(kunder.size() - 1).getId() == id))
                    kunder.get(kunder.size() - 1).addOrder(bestid);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kunder;
    }

    public void addToCart(int kundid, int bestID, int skoID) {

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"),
                p.getProperty("pass"))) {

            CallableStatement cstmt = con.prepareCall("CALL AddToCart(?, ?, ?)");
            cstmt.setInt(1, kundid);
            cstmt.setInt(2, bestID);
            cstmt.setInt(3, skoID);

            cstmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Beställningen gick igenom!");
        getAllOrders();
    }

}
