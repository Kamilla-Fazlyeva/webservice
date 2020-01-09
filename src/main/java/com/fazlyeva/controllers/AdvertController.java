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
import java.util.List;


public class AdvertController {

    private IAdvertService advertService = new AdvertService();

    public AdvertController() throws SQLException {
    }

    @Path("/adverts")
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

    @Path("/advert/{id}")
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


    @Path("/advert/{id}")
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

    @Path("/advert/{id}")
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
