package com.fazlyeva.dao;

import com.fazlyeva.model.Person;
import java.util.List;

public interface IPersonDao {

    public List<Person> getAllPersons();

    public boolean createPerson(Person person);

    public Person getPersonById(int id);

    public boolean editPerson(Person person, int id);

    public boolean deletePerson(int id);
}
