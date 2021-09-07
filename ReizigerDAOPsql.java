package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO<Reiziger> {
    Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
            mystmt.setInt(1, reiziger.getReiziger_id());
            mystmt.setString(2, reiziger.getVoorletters());
            mystmt.setString(3, reiziger.getTussenvoegsel());
            mystmt.setString(4, reiziger.getAchternaam());
            mystmt.setDate(5, reiziger.getGeboortedatum());
            mystmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?");
            mystmt.setInt(5, reiziger.getReiziger_id());
            mystmt.setString(1, reiziger.getVoorletters());
            mystmt.setString(2, reiziger.getTussenvoegsel());
            mystmt.setString(3, reiziger.getAchternaam());
            mystmt.setDate(4, reiziger.getGeboortedatum());
            mystmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id=?");
            mystmt.setInt(1, reiziger.getReiziger_id());
            mystmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        List l1 = new ArrayList<Reiziger>();
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reiziger");
            System.out.println("Alle reizigers:");
            while (rs.next()) {
                int id = rs.getInt(1);
                String voorletters = rs.getString(2);
                String tussenvoegsel = rs.getString(3);
                String achternaam = rs.getString(4);
                Date datum = rs.getDate(5);

                Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, datum);
                l1.add(reiziger);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l1;
    }
}
