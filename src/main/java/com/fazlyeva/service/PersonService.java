package com.fazlyeva.service;

import com.fazlyeva.dao.IPersonDao;
import com.fazlyeva.dao.PersonDao;
import com.fazlyeva.model.Person;
import java.sql.SQLException;
import java.util.List;

public class PersonService implements IPersonService {

    private IPersonDao personDao = new PersonDao();

    public PersonService() throws SQLException {
    }

    @Override
    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public boolean createPerson(Person person) {
        return personDao.createPerson(person);
    }

    @Override
    public Person getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    @Override
    public boolean editPerson(Person person, int id) {
        return personDao.editPerson(person, id);
    }

    @Override
    public boolean deletePerson(int id) {
        return personDao.deletePerson(id);
    }
}
