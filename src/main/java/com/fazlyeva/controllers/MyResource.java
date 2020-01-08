package com.fazlyeva.controllers;

import com.fazlyeva.model.Person;
import com.fazlyeva.service.IPersonService;
import com.fazlyeva.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/person")
public class MyResource {

    private IPersonService personService = new PersonService();

    public MyResource() throws SQLException {
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
}
