package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
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

@Path("/advert")
public class AdvertController {

    private IAdvertService advertService = new AdvertService();

    public AdvertController() throws SQLException {
    }

    @Operation(summary = "List of all adverts",
            responses = {
                    @ApiResponse(description = "This operation gets the full list of person's adverts in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "200", description = "All adverts in the database are found"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllAdverts() {

        List<Advert> advertList = advertService.getAllAdverts();
        if (!advertList.isEmpty()) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromAdvertList = jsonBuilder.toJson(advertList);
            return Response.ok(jsonFromAdvertList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Get advert by id", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation gets the advert by its id in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "200", description = "All by id is found"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAdvertById(@Parameter(description = "Advert id that need to get the advert's body",
            required = true) @PathParam("id") Integer id) {

        Advert advert = advertService.getAdvertById(id);

        if (advert != null) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromAdvert = jsonBuilder.toJson(advert);
            return Response.ok(jsonFromAdvert).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new advert",
            responses = {
                    @ApiResponse(description = "This operation creates a new advert in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "201", description = "New advert is created"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAdvert(Advert advert, @PathParam("person_id") Integer person_id) {

        advert.setPerson_id(person_id);
        boolean success = advertService.createAdvert(advert);

        if (success) {
            return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Operation(summary = "Updates the advert", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation updates the data of advert in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "204", description = "Advert is updated"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAdvert(Advert advert, @PathParam("id") Integer id) {

        boolean success = advertService.editAdvert(advert, id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Operation(summary = "Delete the advert", operationId = "id",
            responses = {
                    @ApiResponse(description = "This operation deletes the person's advert in database",
                            content = @Content(schema = @Schema(implementation = Advert.class))),
                    @ApiResponse(responseCode = "204", description = "Advert is deleted"),
                    @ApiResponse(responseCode = "304", description = "Not modified")
            })
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdvert(@Parameter(description = "Advert's id that need ro be deleted from database",
            required = true) @PathParam("id") Integer id) {

        boolean success = advertService.deleteAdvert(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }
}
