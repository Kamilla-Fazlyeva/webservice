package com.fazlyeva.dao;

import com.fazlyeva.connection.DBConnection;
import com.fazlyeva.model.Advert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertDao implements IAdvertDao{

    Connection dbConnection = DBConnection.getDBConnection();

    public AdvertDao() throws SQLException {
    }

    @Override
    public List<Advert> getAllAdverts() {

        List<Advert> advertList = new ArrayList<Advert>();

        try {
            PreparedStatement pr = dbConnection.prepareStatement("select * from advert order by advert_id");
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Advert advert = new Advert();
                advert.setId(rs.getInt(1));
                advert.setPerson_id(rs.getInt(2));
                advert.setHeader(rs.getString(3));
                advert.setBody(rs.getString(4));
                advert.setCategory(rs.getString(5));
                advert.setPhone(rs.getString(6));
                advert.setDateTime(rs.getTimestamp(7).toLocalDateTime());
                advertList.add(advert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advertList;
    }

    @Override
    public List<Advert> getAllAdvertsByPersonId(int person_id) {

        List<Advert> advertList = new ArrayList<Advert>();

        try {
            PreparedStatement pr = dbConnection.prepareStatement("select * from advert where person_id=?");
            pr.setInt(1, person_id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Advert advert = new Advert();
                advert.setId(rs.getInt(1));
                advert.setPerson_id(rs.getInt(2));
                advert.setHeader(rs.getString(3));
                advert.setBody(rs.getString(4));
                advert.setCategory(rs.getString(5));
                advert.setPhone(rs.getString(6));
                advert.setDateTime(rs.getTimestamp(7).toLocalDateTime());
                advertList.add(advert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advertList;
    }

    @Override
    public Advert getAdvertById(int id) {

        Advert advert = null;

        try {
            PreparedStatement pr = dbConnection.prepareStatement("select * from advert where advert_id=?");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                advert = new Advert();
                advert.setId(rs.getInt(1));
                advert.setPerson_id(rs.getInt(2));
                advert.setHeader(rs.getString(3));
                advert.setBody(rs.getString(4));
                advert.setCategory(rs.getString(5));
                advert.setPhone(rs.getString(6));
                advert.setDateTime(rs.getTimestamp(7).toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advert;
    }

    @Override
    public boolean createAdvert(Advert advert) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("insert into advert "
                    + "(person_id, header, body, category, phone, date) values (?, ?, ?, ?, ?, ?)");
            pr.setInt(1, advert.getPerson_id());
            pr.setString(2, advert.getHeader());
            pr.setString(3, advert.getBody());
            pr.setString(4, advert.getCategory());
            pr.setString(5, advert.getPhone());
            pr.setTimestamp(6, Timestamp.valueOf(advert.getDateTime()));
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editAdvert(Advert advert, int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("update advert set "
                    + "header=?, body=?, category=?, phone=? where advert_id=?");
            pr.setString(1,advert.getHeader());
            pr.setString(2, advert.getBody());
            pr.setString(3, advert.getCategory());
            pr.setString(4, advert.getPhone());
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAdvert(int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("delete from advert where advert_id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
