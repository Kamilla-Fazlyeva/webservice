package com.fazlyeva.controllers;

import com.fazlyeva.model.Advert;
import com.fazlyeva.service.AdvertService;
import com.fazlyeva.service.IAdvertService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Path("/advert")
public class AdvertController {

    private IAdvertService advertService = new AdvertService();

    public AdvertController() throws SQLException {
    }

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

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAdvertById(@PathParam("id") Integer id) {

        Advert advert = advertService.getAdvertById(id);

        if (advert != null) {
            Gson jsonBuilder = new GsonBuilder().create();
            String jsonFromAdvert = jsonBuilder.toJson(advert);
            return Response.ok(jsonFromAdvert).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

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

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateAdvert(@FormParam("header") String header, @FormParam("body") String body,
                                 @FormParam("category") String category, @FormParam("phone") String phone,
                                 @PathParam("id") Integer id) {

        Advert advert = new Advert();
        advert.setId(id);
        advert.setHeader(header);
        advert.setBody(body);
        advert.setCategory(category);
        advert.setPhone(phone);

        boolean success = advertService.editAdvert(advert, id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAdvert(@PathParam("id") Integer id) {

        boolean success = advertService.deleteAdvert(id);

        if (success) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.notModified().build();
        }
    }
}
