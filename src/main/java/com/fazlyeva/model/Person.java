package com.fazlyeva.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Person")
public class Person {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String category;

    public Person() {
    }

    public Person(int id, String name, String surname, String email, String category) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ApiModelProperty(name = "Person's unique id in database")
    public int getId() {
        return id;
    }

    @ApiModelProperty(name = "Person's name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(name = "Person's surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ApiModelProperty(name = "Person's unique email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(name = "Person's category: individual or entity (company)")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
