package com.company.DAO;

import com.company.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO<Reiziger> {
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public boolean save(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            PreparedStatement mystmt = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
            mystmt.setInt(1, reiziger.getReiziger_id());
            mystmt.setString(2, reiziger.getVoorletters());
            mystmt.setString(3, reiziger.getTussenvoegsel());
            mystmt.setString(4, reiziger.getAchternaam());
            mystmt.setDate(5, reiziger.getGeboortedatum());
            mystmt.executeQuery();
            mystmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            PreparedStatement mystmt = conn.prepareStatement("UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?");
            mystmt.setInt(5, reiziger.getReiziger_id());
            mystmt.setString(1, reiziger.getVoorletters());
            mystmt.setString(2, reiziger.getTussenvoegsel());
            mystmt.setString(3, reiziger.getAchternaam());
            mystmt.setDate(4, reiziger.getGeboortedatum());
            mystmt.executeQuery();
            mystmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            PreparedStatement mystmt = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id=?");
            mystmt.setInt(1, reiziger.getReiziger_id());
            mystmt.executeQuery();
            mystmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger r1 = new Reiziger(0, null, null, null, null);
        try {
            // 1. Get a connection to database
            PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id=?");
            mystmt.setInt(1, id);
            ResultSet rs = mystmt.executeQuery("SELECT * FROM reiziger");
            r1.setReiziger_id(rs.getInt(1));
            r1.setVoorletters(rs.getString(2));
            r1.setTussenvoegsel(rs.getString(3));
            r1.setAchternaam(rs.getString(4));
            r1.setGeboortedatum(rs.getDate(5));
            mystmt.close();
            rs.close();
            return r1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        try {
            PreparedStatement mystmt = conn.prepareStatement("SELECT * from reiziger WHERE geboortedatum =?");
            mystmt.setDate(1, Date.valueOf(datum));
            ResultSet rs = mystmt.executeQuery();
            while (rs.next()) {
                Reiziger reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                reizigers.add(reiziger);
            }
            mystmt.close();
            rs.close();
            return reizigers;
        } catch (SQLException sqlException) {
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        List l1 = new ArrayList<Reiziger>();
        try {
            // 1. Get a connection to database
            PreparedStatement st = conn.prepareStatement("SELECT * FROM reiziger");
            ResultSet rs = st.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l1;
    }
}
