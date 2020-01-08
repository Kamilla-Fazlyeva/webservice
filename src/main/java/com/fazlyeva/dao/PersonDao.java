package com.fazlyeva.dao;

import com.fazlyeva.connection.PersonDB;
import com.fazlyeva.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements IPersonDao {

    Connection dbConnection = PersonDB.getDBConnection();

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
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setSurname(rs.getString(3));
                person.setEmail(rs.getString(4));
                person.setCategory(rs.getString(5));
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
                person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setSurname(rs.getString(3));
                person.setEmail(rs.getString(4));
                person.setCategory(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean deletePerson(int id) {
        try {
            PreparedStatement pr = dbConnection.prepareStatement("delete from person where user_id=?");
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
