package com.fazlyeva.service;

import com.fazlyeva.model.Person;
import java.util.List;

public interface IPersonService {

    public List<Person> getAllPersons();

    public boolean createPerson(Person person);

    public Person getPersonById(int id);

    public boolean editPerson(Person person, int id);

    public boolean deletePerson(int id);
}
