package com.fazlyeva.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class Person {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String category;

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "Person's unique id in database")
    public Integer getId() {
        return id;
    }

    @XmlElement(name = "Person's name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Person's surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement(name = "Person's unique email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "Person's category: individual or entity (company)")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
