package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.model.Person;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
import com.fazlyeva.service.IPersonService;
import com.fazlyeva.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/person")
public class PersonController {

    private IPersonService personService = new PersonService();
    private IAdvertService advertService = new AdvertService();

    public PersonController() throws SQLException {
    }

    @ApiOperation(value = "Get all persons in the database",
            response = Person.class)
                    @ApiResponses(value = {
                            @ApiResponse(code = 200, message = "All persons in the database are found"),
                            @ApiResponse(code = 404, message = "Not found")
     })
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

    @ApiOperation(value = "Get person by its id",
            response = Person.class)
                    @ApiResponses(value = {
                            @ApiResponse(code = 200, message = "Person is found"),
                            @ApiResponse(code = 404, message = "Not found")
            })
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonById(@ApiParam(value = "Person id that needs to get the person's data",
                                       required = true) @PathParam("id") int id) {

        Person person = personService.getPersonById(id);

        if (person != null) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromPerson = jsonBuilder.toJson(person);
           return Response.ok(jsonFromPerson).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "Create a new person",
            response = Person.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Person is created"),
                        @ApiResponse(code = 304, message = "Not modified")
            })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPerson(Person person) {

        boolean success = personService.createPerson(person);

        if (success) {
            return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.notModified().build();
        }
    }

    @ApiOperation(value = "Update the data of person",
            response = Person.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 204, message = "Person is updated"),
                        @ApiResponse(code = 304, message = "Not modified")
            })
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person, @PathParam("id") Integer id) {

        boolean success = personService.editPerson(person, id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @ApiOperation(value = "Delete the person",
            response = Person.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 204, message = "Person is deleted"),
                        @ApiResponse(code = 304, message = "Not modified")
            })
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePerson(@ApiParam(value = "Person's id that needs to be deleted from database",
            required = true) @PathParam("id") int id) {

        boolean success = personService.deletePerson(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @ApiOperation(value = "Person's adverts",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "All adverts of the person are found"),
                        @ApiResponse(code = 304, message = "Not found")
            })
    @Path("/{person_id}/advert")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdvertsByPersonId(@ApiParam(value = "Person's id that needs to get all of its adverts",
            required = true) @PathParam("person_id") Integer person_id) {

        List<Advert> advertList = advertService.getAllAdvertsByPersonId(person_id);

        if (!advertList.isEmpty()) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromAdvertList = jsonBuilder.toJson(advertList);
            return Response.ok(jsonFromAdvertList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
