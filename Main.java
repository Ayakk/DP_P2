package P2;

import P2.Domain.Reiziger;
import P2.DAO.ReizigerDAO;
import P2.DAO.ReizigerDAOPsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {



    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
        ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(conn);
        testReizigerDAO(reizigerDAOPsql);
    }

    private void getConnection(){


    }

    private void closeConnection(){

    }

    private static void testReizigerDAO (ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");



        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        Reiziger updater = new Reiziger(77, "U", "", "UUUU", java.sql.Date.valueOf(gbdatum));
        rdao.update(updater);
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

        Reiziger deleter = new Reiziger(77, "U", "", "UUUU", java.sql.Date.valueOf(gbdatum));
        rdao.delete(deleter);
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

    }
}
