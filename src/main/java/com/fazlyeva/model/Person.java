package com.fazlyeva.model;

public class Person {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String category;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return "Person{" + "name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                ", id = '" + id + '\'' +
                ", email = '" + email + '\'' +
                ", category ='" + category + '\'' +
                '}';
    }
}
