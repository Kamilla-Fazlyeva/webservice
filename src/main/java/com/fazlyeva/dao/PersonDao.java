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

public class PersonDao implements IPersonDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);
    Connection dbConnection = DBConnection.getDBConnection();

    public PersonDao() throws SQLException {
    }

    @Override
    public List<Person> getAllPersons() {

        List<Person> personList = new ArrayList<Person>();

        try {
            PreparedStatement pr = dbConnection.prepareStatement("select user_id, username, surname, email, " +
                    "category from person order by user_id");
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                Person person = new Person(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                personList.add(person);
            }
        } catch (SQLException e) {
            logger.error("Connection to database failed", e);
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return personList;
    }

    @Override
    public Person getPersonById(int id) {
        Person person = null;
        try {
            PreparedStatement pr = dbConnection.prepareStatement("select user_id, username, surname, email, " +
                    "category from person where user_id=?");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                person = new Person(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            logger.error("Connection to database failed", e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return person;
    }

    @Override
    public boolean createPerson(Person person) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("insert into person "
                    + "(username, surname, email, category) values (?, ?, ?, ?)");
            pr.setString(1, person.getName());
            pr.setString(2, person.getSurname());
            pr.setString(3, person.getEmail());
            pr.setString(4, person.getCategory());
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Connection to database failed", e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return false;
    }

    @Override
    public boolean editPerson(Person person, int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("update person set "
                    + "username=?, surname=?, email=?, category=? where user_id=?");
            pr.setString(1, person.getName());
            pr.setString(2, person.getSurname());
            pr.setString(3, person.getEmail());
            pr.setString(4, person.getCategory());
            pr.setInt(5, person.getId());
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Connection to database failed", e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return false;
    }

    @Override
    public boolean deletePerson(int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("delete from person where user_id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Connection to database failed", e);;
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.error("Connection error", e);
        }
        return false;
    }
}
