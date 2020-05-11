package com.fazlyeva.dao;

import com.fazlyeva.connection.DBConnection;
import com.fazlyeva.model.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.fazlyeva.enums.WebServiceConstants.DatabaseQuery.*;
import static com.fazlyeva.enums.WebServiceConstants.LoggerMessages.*;

public class AdvertDao implements IAdvertDao{

    Logger logger = LoggerFactory.getLogger(AdvertDao.class);
    Connection dbConnection = DBConnection.getDBConnection();

    public AdvertDao() throws SQLException {
    }

    @Override
    public List<Advert> getAllAdverts() {

        List<Advert> advertList = new ArrayList<Advert>();

        try {
            PreparedStatement pr = dbConnection.prepareStatement(SELECT_ADVERT_ALL.value);
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
                logger.info(INFO_GET_ADVERTS.value);
            }
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return advertList;
    }

    @Override
    public List<Advert> getAllAdvertsByPersonId(int person_id) {

        List<Advert> advertList = new ArrayList<Advert>();

        try {
            PreparedStatement pr = dbConnection.prepareStatement(SELECT_ADVERT_BY_PERSON_ID.value);
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
                logger.info(INFO_GET_PERSON_ADVERTS.value);
            }
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return advertList;
    }

    @Override
    public Advert getAdvertById(int id) {
        Advert advert = null;
        try {
            PreparedStatement pr = dbConnection.prepareStatement(SELECT_ADVERT_BY_ID.value);
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
                logger.info(INFO_GET_ADVERT.value);
            }
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return advert;
    }

    @Override
    public boolean createAdvert(Advert advert) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(CREATE_ADVERT.value);
            pr.setInt(1, advert.getPerson_id());
            pr.setString(2, advert.getHeader());
            pr.setString(3, advert.getBody());
            pr.setString(4, advert.getCategory());
            pr.setString(5, advert.getPhone());
            pr.setTimestamp(6, Timestamp.valueOf(advert.getDateTime()));
            pr.executeUpdate();
            logger.info(INFO_CREATE_ADVERT.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }

    @Override
    public boolean editAdvert(Advert advert, int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(UPDATE_ADVERT.value);
            pr.setString(1,advert.getHeader());
            pr.setString(2, advert.getBody());
            pr.setString(3, advert.getCategory());
            pr.setString(4, advert.getPhone());
            pr.setInt(5, advert.getId());
            pr.executeUpdate();
            logger.info(INFO_UPDATE_ADVERT.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }

    @Override
    public boolean deleteAdvert(int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(DELETE_ADVERT.value);
            pr.setInt(1, id);
            pr.executeUpdate();
            logger.info(INFO_DELETE_ADVERT.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }
}
