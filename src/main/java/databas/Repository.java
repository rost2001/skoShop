package databas;

/*
public class Repository {
    private Properties p = new Properties();
    List<Sko> allaSko = new ArrayList<>();
    List<Kund> allaKunder = new ArrayList<>();
    int kundID;


    public Repository() {
        try {
            p.load(new FileInputStream("src/main/java/model/Settings.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kund> getAllCustomer() {
        String query = "SELECT * FROM skoshop.kund;";
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"))) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String förnamn = rs.getString("Förnamn");
                String efternamn = rs.getString("efternamn");
                String ort = rs.getString("ort");
                String lösenord = rs.getString("lösenord");
                allaKunder.add(new Kund(id, förnamn, efternamn, ort, lösenord));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaKunder;
    }

    public boolean checkCustomerStatus(String förnamn, String lösenord, List<Kund> kundList) {
        boolean kundHosOss = false;
        for (Kund k : kundList) {
            if (k.getFörnamn().equalsIgnoreCase(förnamn) && k.getLösenord().equals(lösenord)) {
                kundHosOss = true;
                System.out.println("Välkommen " + k.getFörnamn() + " " + k.getEfternamn());
                kundID = k.getId();//Kunden som loggat i
                break;
            }
        }
        return kundHosOss;
    }


    public List<Sko> getAllShoes() {
        String query = "SELECT skor.id, skor.pris, skor.färg, märke.id, märke.namn, kategori.id, kategori.namn, storlek.id, storlek.nummer, finns_i.antal\n" +
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
                int skoID = rs.getInt("skor.id");
                int skoPris = rs.getInt("skor.pris");
                String skoFärg = rs.getString("skor.färg");

                int skoMärkeID = rs.getInt("märke.id");
                String skoMärkeNamn = rs.getString("märke.namn");
                Märke skoMärke = new Märke(skoMärkeID, skoMärkeNamn);

                int katId = rs.getInt("kategori.id");
                String katName = rs.getString("kategori.namn");
                Kategori skoKategori = new Kategori(katId, katName);

                int skoStorlekId = rs.getInt("storlek.id");
                int skoStorlekNummer = rs.getInt("storlek.nummer");
                Storlek skoStorlek = new Storlek(skoStorlekId, skoStorlekNummer);

                int skoAntalILager = rs.getInt("finns_i.antal");

                Sko sko = new Sko(skoID, skoPris, skoFärg, skoMärke, skoKategori, skoStorlek, skoAntalILager);
                allaSko.add(sko);
            }

            for (int i = 0; i < allaSko.size(); i++) {

                System.out.println(allaSko.get(i).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allaSko;
    }

    public int getOrderId(int kund_id) {
        int orderId = 0;
        String query = "SELECT kund.id, beställning.id\n" +
                "from kund\n" +
                "join BESTÄLLNING\n" +
                "on BESTÄLLNING.Kundid=KUND.id\n" +
                "where BESTÄLLNING.Kundid=?;";
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"));
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, kund_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderId = rs.getInt("beställning.id");
                //System.out.println("your order id is: "+orderId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void addShoesToCart(String märkeNamn, String skoKategoriNamn, String skoStorlekNummer) {
        int shoeID = 0;
        String query = "SELECT skor.id, skor.pris, skor.färg, märke.id, märke.namn, kategori.id, kategori.namn, storlek.id, storlek.nummer, finns_i.antal\n" +
                "from skor\n" +
                "join märke\n" +
                "on skor.märkesid = märke.id\n" +
                "join kategori\n" +
                "on skor.katid = kategori.id\n" +
                "join finns_i\n" +
                "on finns_i.skoid = skor.id\n" +
                "join storlek\n" +
                "on finns_i.storid = storlek.id\n" +
                "where märke.namn=? \n" +
                "and kategori.namn=? \n" +
                "and storlek.nummer=?;";
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"), p.getProperty("user"), p.getProperty("pass"));
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, märkeNamn);
            stmt.setString(2, skoKategoriNamn);
            stmt.setString(3, skoStorlekNummer);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                shoeID = rs.getInt("skor.id");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("your shoeID is " + shoeID);
        //return shoeID;
        if (shoeID > 0) {
            addToCart(kundID, getOrderId(kundID), shoeID);
        }
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
        System.out.println("Product was added to the cart");
    }

    public void showMyCart() {
    }
}


*/




