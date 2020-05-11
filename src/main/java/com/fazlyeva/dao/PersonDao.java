package com.fazlyeva.dao;

import com.fazlyeva.connection.DBConnection;
import com.fazlyeva.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.fazlyeva.enums.WebServiceConstants.DatabaseQuery.*;
import static com.fazlyeva.enums.WebServiceConstants.LoggerMessages.*;

public class PersonDao implements IPersonDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);
    Connection dbConnection = DBConnection.getDBConnection();

    public PersonDao() throws SQLException {
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<Person>();
        try {
            PreparedStatement pr = dbConnection.prepareStatement(SELECT_PERSON_ALL.value);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Person person = new Person(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                personList.add(person);
            }
            logger.info(INFO_GET_PERSONS.value);

        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return personList;
    }

    @Override
    public Person getPersonById(int id) {
        Person person = null;
        try {
            PreparedStatement pr = dbConnection.prepareStatement(SELECT_PERSON_BY_ID.value);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                person = new Person(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
            logger.info(INFO_GET_PERSON.value);
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return person;
    }

    @Override
    public boolean createPerson(Person person) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(CREATE_PERSON.value);
            pr.setString(1, person.getName());
            pr.setString(2, person.getSurname());
            pr.setString(3, person.getEmail());
            pr.setString(4, person.getCategory());
            pr.executeUpdate();
            logger.info(INFO_CREATE_PERSON.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }

    @Override
    public boolean editPerson(Person person, int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(UPDATE_PERSON.value);
            pr.setString(1, person.getName());
            pr.setString(2, person.getSurname());
            pr.setString(3, person.getEmail());
            pr.setString(4, person.getCategory());
            pr.setInt(5, person.getId());
            pr.executeUpdate();
            logger.info(INFO_UPDATE_PERSON.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }

    @Override
    public boolean deletePerson(int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement(DELETE_PERSON.value);
            pr.setInt(1, id);
            pr.executeUpdate();
            logger.info(INFO_DELETE_PERSON.value);
            return true;
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error(ERROR_SQL.value, e);
        }
        return false;
    }
}
