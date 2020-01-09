package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.model.Person;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
import com.fazlyeva.service.IPersonService;
import com.fazlyeva.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Path("/person")
public class PersonController {

    private IPersonService personService = new PersonService();
    private IAdvertService advertService = new AdvertService();

    public PersonController() throws SQLException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllPersons() {

        List<Person> personList = personService.getAllPersons();
        if (!personList.isEmpty()) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromPersonsList = jsonBuilder.toJson(personList);
            return Response.ok(jsonFromPersonsList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonById(@PathParam("id") Integer id) {

        Person person = personService.getPersonById(id);

        if (person != null) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromPerson = jsonBuilder.toJson(person);
           return Response.ok(jsonFromPerson).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newPerson(@FormParam("name") String name, @FormParam("surname") String surname,
                              @FormParam("email") String email, @FormParam("category") String category) {

        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);
        person.setCategory(category);

        boolean success = personService.createPerson(person);

        if (success) {
            return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updatePerson(@FormParam("name") String name, @FormParam("surname") String surname,
                                 @FormParam("email") String email, @FormParam("category") String category,
                                 @PathParam("id") Integer id) {

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);
        person.setCategory(category);

        boolean success = personService.editPerson(person, id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePerson(@PathParam("id") Integer id) {

        boolean success = personService.deletePerson(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Path("/{person_id}/adverts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdvertsByPersonId(@PathParam("person_id") Integer person_id) {

        List<Advert> advertList = advertService.getAllAdvertsByPersonId(person_id);

        if (!advertList.isEmpty()) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromAdvertList = jsonBuilder.toJson(advertList);
            return Response.ok(jsonFromAdvertList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{person_id}/advert")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newAdvert(@FormParam("header") String header, @FormParam("body") String body,
                              @FormParam("category") String category, @FormParam("phone") String phone,
                              @FormParam("date") String dateTime, @PathParam("person_id") Integer person_id) {

        Advert advert = new Advert();
        advert.setHeader(header);
        advert.setBody(body);
        advert.setCategory(category);
        advert.setPhone(phone);
        advert.setPerson_id(person_id);
        try {
            advert.setDateTime(LocalDateTime.parse(dateTime));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        boolean success = advertService.createAdvert(advert);

        if (success) {
            return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.notModified().build();
        }
    }
}
