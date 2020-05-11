package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
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

@Path("/advert")
public class AdvertController {

    private IAdvertService advertService = new AdvertService();

    public AdvertController() throws SQLException {
    }

    @ApiOperation(value = "Get all adverts in the database",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "All adverts in the database are found"),
                        @ApiResponse(code = 404, message = "Not found")
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

    @ApiOperation(value = "Get advert by its id",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Advert by id is found"),
                        @ApiResponse(code = 404, message = "Not found")
            })
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAdvertById(@ApiParam(value = "Advert's id that needs to get the advert's body",
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

    @ApiOperation(value = "Create a new advert",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "New advert is created"),
                        @ApiResponse(code = 304, message = "Not modified")
            })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAdvert(Advert advert, @PathParam("person_id") Integer person_id) {

        boolean success = advertService.createAdvert(advert);

        if (success) {
            return Response.ok().status(Response.Status.CREATED).build();
        } else {
            return Response.notModified().build();
        }
    }

    @ApiOperation(value = "Update the advert",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 204, message = "Advert is updated"),
                        @ApiResponse(code = 304, message = "Not modified")
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

    @ApiOperation(value = "Delete the advert",
            response = Advert.class)
                    @ApiResponses(value = {
                        @ApiResponse(code = 204, message = "Advert is deleted"),
                        @ApiResponse(code = 304, message = "Not modified")
            })
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdvert(@ApiParam(value = "Advert's id that needs ro be deleted from database",
            required = true) @PathParam("id") Integer id) {

        boolean success = advertService.deleteAdvert(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }
}
