package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.model.Person;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
import com.fazlyeva.service.IPersonService;
import com.fazlyeva.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Find all persons in database",
            responses = {
                    @ApiResponse(description = "This operation gets the full list of person in database",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(responseCode = "200", description = "All persons in the database are found"),
                    @ApiResponse(responseCode = "404", description = "Not found")
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

    @Operation(summary = "Find person by its id", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation gets the data of person by id",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(responseCode = "200", description = "Person is found"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPersonById(@Parameter(description = "Person id that need to get the person's data",
                                       required = true) @PathParam("id") Integer id) {

        Person person = personService.getPersonById(id);

        if (person != null) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromPerson = jsonBuilder.toJson(person);
           return Response.ok(jsonFromPerson).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new person",
            responses = {
                    @ApiResponse(description = "This operation creates a new person in database",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(responseCode = "201", description = "Person is created"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newPerson(@Parameter(description = "Name of a new person", required = true)
                                  @FormParam("name") String name,
                              @Parameter(description = "Surname of a new person", required = true)
                              @FormParam("surname") String surname,
                              @Parameter(description = "New person's unique email", required = true)
                                  @FormParam("email") String email,
                              @Parameter(description = "New person's category: individual or entity (company)",
                                      required = true) @FormParam("category") String category) {

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

    @Operation(summary = "Update the data of person", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation updates the data of person in database",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(responseCode = "204", description = "Person is updated"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updatePerson(@QueryParam(value = "Person's name that need to be updated")
                                     @FormParam("name") String name,
                                 @QueryParam(value = "Person's surname that need to be updated")
                                 @FormParam("surname") String surname,
                                 @QueryParam(value = "Person's email that need to be updated")
                                     @FormParam("email") String email, @FormParam("category") String category,
                                 @Parameter (description = "Person's id that need to be updated", required = true)
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

    @Operation(summary = "Delete the person", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation deletes all of the data of person in database",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(responseCode = "204", description = "Person is deleted"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePerson(@Parameter (description = "Person's id that need to be deleted from database",
            required = true) @PathParam("id") Integer id) {

        boolean success = personService.deletePerson(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Operation(summary = "Person's adverts", operationId = "person_id",
            responses = {
                    @ApiResponse(description = "This operation gets all of the person's adverts in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "200", description = "All adverts of the person are found"),
                    @ApiResponse(responseCode = "304", description = "Not found")
            })
    @Path("/{person_id}/advert")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdvertsByPersonId(@Parameter (description = "Person's id that need to get all of his adverts",
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
